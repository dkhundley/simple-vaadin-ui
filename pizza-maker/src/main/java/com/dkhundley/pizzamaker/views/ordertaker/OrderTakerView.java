// Importing all the special code we need to build our Vaadin UI
package com.dkhundley.pizzamaker.views.ordertaker;
import com.dkhundley.pizzamaker.views.confirmationscreen.ConfirmationScreenView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.QueryParameters;

@PageTitle("Order Taker")
@Route("")
public class OrderTakerView extends Composite<VerticalLayout> {

    // VARIABLE INSTANTIATION
    // --------------------------------------------------------------------------------------------------------------------
    // Instantiating components that we will directly interact with for computations
    private float subtotal = 0.0f;
    private float grandTotal = 0.0f;
    private float taxPercentage = 0.07f;
    private float taxAmount = 0.0f;
    private float tipAmount = 0.0f;
    private Select<PizzaItem> crustSizeSelect;
    private MultiSelectComboBox<PizzaItem> meatsSelect;
    private MultiSelectComboBox<PizzaItem> veggiesSelect;
    private H4 subtotalValueLabel;
    private H4 tipAmountValueLabel;
    private H4 grandTotalValueLabel;



    // COMPUTATIONAL FUNCTIONS
    // --------------------------------------------------------------------------------------------------------------------
    // Defining a function to update the subtotal value based on the selected pizza options
    private void updateSubtotal(PizzaItem crustSize, Set<PizzaItem> meats, Set<PizzaItem> veggies, H4 subtotalValueLabel) {
        subtotal = 0.0f;
        if (crustSize != null) {
            subtotal += crustSize.value();
        }
        subtotal += meats.stream().map(PizzaItem::value).reduce(0.0f, Float::sum);
        subtotal += veggies.stream().map(PizzaItem::value).reduce(0.0f, Float::sum);
        subtotalValueLabel.setText(String.format("$%.2f", subtotal));
    }

    // Defining a function to update the grand total value based on the subtotal and selected tip
    private void updateGrandTotal(float subtotal, float tipAmount, H4 grandTotalValueLabel) {
        this.grandTotal = subtotal + tipAmount + (subtotal * taxPercentage);
        this.taxAmount = subtotal * taxPercentage;
        grandTotalValueLabel.setText(String.format("$%.2f", this.grandTotal));
    }



    // VAADIN UI COMPONENTS
    // --------------------------------------------------------------------------------------------------------------------
    public OrderTakerView() {

        // Setting the primary title and subtitle
        H1 title = new H1("Pizza Maker");
        title.setWidth("max-content");
        H2 subtitle = new H2("Create your pizza below!");
        subtitle.setWidth("max-content");

        // Creating a divider between our section header and pizza selectors
        Hr separator1 = new Hr();

        // Setting the header for the pizza toppings section
        H3 toppingsTitle = new H3("Select Your Toppings");
        toppingsTitle.setWidth("max-content");

        // Setting the general form layout for the pizza options
        FormLayout pizzaOptionsForm = new FormLayout();
        pizzaOptionsForm.setWidth("100%");
        pizzaOptionsForm.setResponsiveSteps(
            new ResponsiveStep("0", 1),
            new ResponsiveStep("250px", 2),
            new ResponsiveStep("500px", 3)
        );

        // Creating a simple selector to be able to select a single crust option
        this.crustSizeSelect = new Select<>();
        crustSizeSelect.setLabel("Crust Size");
        crustSizeSelect.setWidth("min-content");
        setSelectCrustData(crustSizeSelect);
        crustSizeSelect.addValueChangeListener(e -> updateSubtotal(
            crustSizeSelect.getValue(),
            meatsSelect.getValue(),
            veggiesSelect.getValue(),
            subtotalValueLabel
        ));

        // Creating a multi-select selector to be able to select multiple meat options
        this.meatsSelect = new MultiSelectComboBox<>();
        meatsSelect.setLabel("Meats");
        meatsSelect.setWidth("min-content");
        setSelectMeatsData(meatsSelect);
        meatsSelect.addValueChangeListener(e -> updateSubtotal(
            crustSizeSelect.getValue(),
            meatsSelect.getValue(),
            veggiesSelect.getValue(),
            subtotalValueLabel
        ));

        // Creating a multi-select selector to be able to select multiple veggie options
        this.veggiesSelect = new MultiSelectComboBox<>();
        veggiesSelect.setLabel("Veggies");
        veggiesSelect.setWidth("min-content");
        setSelectVeggiesData(veggiesSelect);
        veggiesSelect.addValueChangeListener(e -> updateSubtotal(
            crustSizeSelect.getValue(),
            meatsSelect.getValue(),
            veggiesSelect.getValue(),
            subtotalValueLabel
        ));
        
        // Creating the subtotal header and label itself
        H4 subtotalLabel = new H4("Subtotal:");
        this.subtotalValueLabel = new H4(String.format("$%.2f", subtotal));
        subtotalValueLabel.setWidth("max-content");

        // Creating another horizontal line separator to separate the pizza selection options from the tip section
        Hr separator2 = new Hr();

        // Creating a section header for adding a tip
        H3 tipTitle = new H3("Add a Tip?");
        tipTitle.setWidth("max-content");

        // Creating radio buttons to represent tip percentage options
        RadioButtonGroup<String> tipPercentageRadioGroup = new RadioButtonGroup<>();
        tipPercentageRadioGroup.setLabel("Percentage");
        tipPercentageRadioGroup.setWidth("min-content");
        tipPercentageRadioGroup.setItems("10%", "15%", "20%", "Custom");
        tipPercentageRadioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

        // Creating the custom tip amount input field
        NumberField customTipAmountField = new NumberField("Custom Amount");
        customTipAmountField.setWidth("min-content");
        customTipAmountField.setVisible(false);
        tipPercentageRadioGroup.addValueChangeListener(event -> {
            String value = event.getValue();
            customTipAmountField.setVisible("Custom".equals(value));
        });

        // Creating the tip amount header, label, and radio button selectors
        H4 tipAmountLabel = new H4("Tip Amount:");
        this.tipAmountValueLabel = new H4("$0.00");
        tipAmountValueLabel.setWidth("max-content");
        tipPercentageRadioGroup.addValueChangeListener(event -> {
            String selected = event.getValue();
            this.tipAmount = 0.0f;
            if (selected != null) {
            if ("Custom".equals(selected)) {
                Double customAmount = customTipAmountField.getValue();
                this.tipAmount = customAmount != null ? customAmount.floatValue() : 0.0f;
            } else {
                float percentage = Float.parseFloat(selected.replace("%", "")) / 100;
                this.tipAmount = subtotal * percentage;
            }
            }
            tipAmountValueLabel.setText(String.format("$%.2f", this.tipAmount));
            updateGrandTotal(subtotal, this.tipAmount, grandTotalValueLabel);
        });
        customTipAmountField.addValueChangeListener(event -> {
            if ("Custom".equals(tipPercentageRadioGroup.getValue())) {
            Double value = event.getValue();
            this.tipAmount = value != null ? value.floatValue() : 0.0f;
            tipAmountValueLabel.setText(String.format("$%.2f", this.tipAmount));
            updateGrandTotal(subtotal, this.tipAmount, grandTotalValueLabel);
            }
        });

        // Creating the grand total header and label
        H4 grandTotalLabel = new H4("Grand Total:");
        this.grandTotalValueLabel = new H4(String.format("$%.2f", grandTotal));

        // Creating the "Submit Order!" button and defining functionality
        Button submitOrderButton = new Button("Submit Order!", event -> {

            // Saving all the form information from this view that we will load back into the Confirmation Screen view
            QueryParameters formInformation = QueryParameters.simple(Map.of(
                "selectedCrustSize", crustSizeSelect.getValue().label(),
                "selectedMeatToppings", String.join(",", meatsSelect.getValue().stream().map(PizzaItem::label).toList()),
                "selectedVeggieToppings", String.join(",", veggiesSelect.getValue().stream().map(PizzaItem::label).toList()),
                "tipAmount", String.format("%.2f", tipAmount),
                "subtotal", String.format("%.2f", subtotal),
                "grandTotal", String.format("%.2f", grandTotal),
                "taxAmount", String.format("%.2f", taxAmount)
            ));
        
            // Navigating to the Confirmation Screen view with the form information
            UI.getCurrent().navigate(ConfirmationScreenView.class, formInformation);
        });
        submitOrderButton.setWidth("min-content");
        submitOrderButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Adding components to the layout
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().add(title, subtitle, separator1, toppingsTitle, pizzaOptionsForm, separator2, tipTitle, tipPercentageRadioGroup, customTipAmountField, tipAmountLabel, tipAmountValueLabel, grandTotalLabel, grandTotalValueLabel, submitOrderButton);
        pizzaOptionsForm.add(crustSizeSelect, meatsSelect, veggiesSelect, subtotalLabel, subtotalValueLabel);
    }

    

    // PIZZA OPTION SELECTORS
    // --------------------------------------------------------------------------------------------------------------------
    // Creating a record to hold data about the pizza options
    record PizzaItem(Float value, String label, Boolean disabled) {}

    // Setting crust size data for the crust selection component
    private void setSelectCrustData(Select<PizzaItem> select) {
        List<PizzaItem> crustOptions = new ArrayList<>();
        crustOptions.add(new PizzaItem(8.00f, "Small ($8.00)", null));
        crustOptions.add(new PizzaItem(10.00f, "Medium ($10.00)", null));
        crustOptions.add(new PizzaItem(12.00f, "Large ($12.00)", null));
        crustOptions.add(new PizzaItem(14.00f, "Extra Large ($14.00)", null));
        select.setItems(crustOptions);
        select.setItemLabelGenerator(PizzaItem::label);
        select.setItemEnabledProvider(item -> !Boolean.TRUE.equals(item.disabled()));
    }

    // Set sample data for Meats MultiSelectComboBox component
    private void setSelectMeatsData(MultiSelectComboBox<PizzaItem> multiSelectComboBox) {
        List<PizzaItem> meatOptions = new ArrayList<>();
        meatOptions.add(new PizzaItem(1.50f, "Pepperoni ($1.50)", null));
        meatOptions.add(new PizzaItem(1.50f, "Sausage ($1.50)", null));
        meatOptions.add(new PizzaItem(1.75f, "Bacon ($1.75)", null));
        meatOptions.add(new PizzaItem(1.50f, "Ham ($1.50)", null));
        multiSelectComboBox.setItems(meatOptions);
        multiSelectComboBox.setItemLabelGenerator(PizzaItem::label);
    }

    // Set sample data for Veggies MultiSelectComboBox component
    private void setSelectVeggiesData(MultiSelectComboBox<PizzaItem> multiSelectComboBox) {
        List<PizzaItem> veggieOptions = new ArrayList<>();
        veggieOptions.add(new PizzaItem(1.00f, "Mushrooms ($1.00)", null));
        veggieOptions.add(new PizzaItem(0.75f, "Onions ($0.75)", null));
        veggieOptions.add(new PizzaItem(0.75f, "Peppers ($0.75)", null));
        veggieOptions.add(new PizzaItem(1.00f, "Olives ($1.00)", null));
        multiSelectComboBox.setItems(veggieOptions);
        multiSelectComboBox.setItemLabelGenerator(PizzaItem::label);
    }

}
