// Importing all the special code we need to build our Vaadin UI
package com.dkhundley.pizzamaker.views.ordertaker;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.ArrayList;
import java.util.List;
import com.vaadin.flow.component.UI;
import com.dkhundley.pizzamaker.views.confirmationscreen.ConfirmationScreenView;
import com.vaadin.flow.router.QueryParameters;
import java.util.Map;

@PageTitle("Order Taker")
@Route("")
public class OrderTakerView extends Composite<VerticalLayout> {

    public OrderTakerView() {
        // Page title
        H1 title = new H1("Pizza Maker");
        title.setWidth("max-content");

        // Subtitle
        H2 subtitle = new H2("Create your pizza below!");
        subtitle.setWidth("max-content");

        // Horizontal line separator
        Hr separator1 = new Hr();

        // Toppings section title
        H3 toppingsTitle = new H3("Select Your Toppings");
        toppingsTitle.setWidth("max-content");

        // Form layout for pizza options
        FormLayout pizzaOptionsForm = new FormLayout();
        pizzaOptionsForm.setWidth("100%");
        pizzaOptionsForm.setResponsiveSteps(
            new ResponsiveStep("0", 1),
            new ResponsiveStep("250px", 2),
            new ResponsiveStep("500px", 3)
        );

        // Crust size selection
        Select<SampleItem> crustSizeSelect = new Select<>();
        crustSizeSelect.setLabel("Crust Size");
        crustSizeSelect.setWidth("min-content");
        setSelectSampleData(crustSizeSelect);

        // Meats selection
        MultiSelectComboBox<SampleItem> meatsSelect = new MultiSelectComboBox<>();
        meatsSelect.setLabel("Meats");
        meatsSelect.setWidth("min-content");
        setMeatsSelectSampleData(meatsSelect);

        // Veggies selection
        MultiSelectComboBox<SampleItem> veggiesSelect = new MultiSelectComboBox<>();
        veggiesSelect.setLabel("Veggies");
        veggiesSelect.setWidth("min-content");
        setVeggiesSelectSampleData(veggiesSelect);

        // Horizontal line separator
        Hr separator2 = new Hr();

        // Tip section title
        H3 tipTitle = new H3("Add a Tip?");
        tipTitle.setWidth("max-content");

        // Tip percentage radio buttons
        RadioButtonGroup<String> tipPercentageRadioGroup = new RadioButtonGroup<>();
        tipPercentageRadioGroup.setLabel("Percentage");
        tipPercentageRadioGroup.setWidth("min-content");
        tipPercentageRadioGroup.setItems("10%", "15%", "20%", "Custom");
        tipPercentageRadioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);

        // Custom tip amount field
        NumberField customTipAmountField = new NumberField("Custom Amount");
        customTipAmountField.setWidth("min-content");
        customTipAmountField.setVisible(false);
        tipPercentageRadioGroup.addValueChangeListener(event -> {
            String value = event.getValue();
            customTipAmountField.setVisible("Custom".equals(value));
        });

        // Submit order button
        Button submitOrderButton = new Button("Submit Order!", event -> {
            boolean isValid = true;
            String errorMessage = "";

            // Ensuring that all the appropriate fields have been filled in
            if (crustSizeSelect.getValue() == null) {
                isValid = false;
                errorMessage += "Please select a crust size.\n";
            }
            if (meatsSelect.getValue().isEmpty()) {
                isValid = false;
                errorMessage += "Please select at least one meat topping.\n";
            }
            if (veggiesSelect.getValue().isEmpty()) {
                isValid = false;
                errorMessage += "Please select at least one veggie topping.\n";
            }
            if (tipPercentageRadioGroup.getValue() == null) {
                isValid = false;
                errorMessage += "Please select a tip percentage.\n";
            } else if ("Custom".equals(tipPercentageRadioGroup.getValue()) && 
                      (customTipAmountField.getValue() == null || customTipAmountField.getValue() <= 0)) {
                isValid = false;
                errorMessage += "Please enter a valid custom tip amount.\n";
            }

            // Submitting the pizza order
            if (isValid) {
                QueryParameters params = QueryParameters.simple(Map.of(
                    "crustSizeValue", crustSizeSelect.getValue().value().toString(),
                    "crustSizeLabel", crustSizeSelect.getValue().label(),
                    "meatsValues", String.join(",", meatsSelect.getValue().stream().map(item -> item.value().toString()).toList()),
                    "meatsLabels", String.join(",", meatsSelect.getValue().stream().map(SampleItem::label).toList()),
                    "veggiesValues", String.join(",", veggiesSelect.getValue().stream().map(item -> item.value().toString()).toList()),
                    "veggiesLabels", String.join(",", veggiesSelect.getValue().stream().map(SampleItem::label).toList()),
                    "tipPercentage", tipPercentageRadioGroup.getValue(),
                    "customTipAmount", customTipAmountField.getValue() != null ? 
                        customTipAmountField.getValue().toString() : ""
                ));
                UI.getCurrent().navigate(ConfirmationScreenView.class, params);
            } else {
                com.vaadin.flow.component.notification.Notification.show(
                    errorMessage,
                    3000,
                    com.vaadin.flow.component.notification.Notification.Position.MIDDLE
                );
            }
        });
        submitOrderButton.setWidth("min-content");
        submitOrderButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Add components to the layout
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().add(title, subtitle, separator1, toppingsTitle, pizzaOptionsForm, separator2, tipTitle, tipPercentageRadioGroup, customTipAmountField, submitOrderButton);
        pizzaOptionsForm.add(crustSizeSelect, meatsSelect, veggiesSelect);
    }

    // Sample item record
    record SampleItem(Float value, String label, Boolean disabled) {
    }

    // Set sample data for Select component
    private void setSelectSampleData(Select<SampleItem> select) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem(8.00f, "Small ($8.00)", null));
        sampleItems.add(new SampleItem(10.00f, "Medium ($10.00)", null));
        sampleItems.add(new SampleItem(12.00f, "Large ($12.00)", null));
        sampleItems.add(new SampleItem(14.00f, "Extra Large ($14.00)", null));
        select.setItems(sampleItems);
        select.setItemLabelGenerator(SampleItem::label);
        select.setItemEnabledProvider(item -> !Boolean.TRUE.equals(item.disabled()));
    }

    // Set sample data for Meats MultiSelectComboBox component
    private void setMeatsSelectSampleData(MultiSelectComboBox<SampleItem> multiSelectComboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem(1.50f, "Pepperoni ($1.50)", null));
        sampleItems.add(new SampleItem(1.50f, "Sausage ($1.50)", null));
        sampleItems.add(new SampleItem(1.75f, "Bacon ($1.75)", null));
        sampleItems.add(new SampleItem(1.50f, "Ham ($1.50)", null));
        multiSelectComboBox.setItems(sampleItems);
        multiSelectComboBox.setItemLabelGenerator(SampleItem::label);
    }

    // Set sample data for Veggies MultiSelectComboBox component
    private void setVeggiesSelectSampleData(MultiSelectComboBox<SampleItem> multiSelectComboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem(1.00f, "Mushrooms ($1.00)", null));
        sampleItems.add(new SampleItem(0.75f, "Onions ($0.75)", null));
        sampleItems.add(new SampleItem(0.75f, "Peppers ($0.75)", null));
        sampleItems.add(new SampleItem(1.00f, "Olives ($1.00)", null));
        multiSelectComboBox.setItems(sampleItems);
        multiSelectComboBox.setItemLabelGenerator(SampleItem::label);
    }
}
