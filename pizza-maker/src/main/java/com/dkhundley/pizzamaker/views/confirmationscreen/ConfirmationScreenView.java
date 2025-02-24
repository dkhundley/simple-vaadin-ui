// Importing all the special code we need to build our Vaadin UI
package com.dkhundley.pizzamaker.views.confirmationscreen;
import com.dkhundley.pizzamaker.views.ordertaker.OrderTakerView;
import java.util.List;
import java.util.Map;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;

// Setting our page route (Note: if this was empty, it would be considered the root or "home" page. Naturally, this is not our home page!)
@PageTitle("Confirmation Screen")
@Route("confirmation-screen")
public class ConfirmationScreenView extends Composite<VerticalLayout> implements HasUrlParameter<String> {

    // LOADING OUR ORDER DETAILS FROM THE ORDER TAKER VIEW
    // --------------------------------------------------------------------------------------------------------------------
    // Instantiating variables to store order details
    private String crustSize;
    private List<String> meats;
    private List<String> veggies;
    private Float tipAmount;
    private Float subtotal;
    private Float grandTotal;

    // Setting the parameters from the pizza order taker view
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        
        // Extracting the values from the previous order taker view
        QueryParameters queryParameters = event.getLocation().getQueryParameters();
        Map<String, List<String>> parameterMap = queryParameters.getParameters();
        
        // Storing values in instance variables
        crustSize = parameterMap.getOrDefault("selectedCrustSize", List.of("")).get(0);
        meats = List.of(parameterMap.getOrDefault("selectedMeatToppings", List.of("")).get(0).split(","));
        veggies = List.of(parameterMap.getOrDefault("selectedVeggieToppings", List.of("")).get(0).split(","));

        // Safely parsing any dollar amount values from string values back into float values
        String customTipStr = parameterMap.getOrDefault("tipAmount", List.of("0")).get(0);
        tipAmount = customTipStr.isEmpty() ? 0f : Float.parseFloat(customTipStr);
        String subtotalStr = parameterMap.getOrDefault("subtotal", List.of("0")).get(0);
        subtotal = subtotalStr.isEmpty() ? 0f : Float.parseFloat(subtotalStr);
        String grandTotalStr = parameterMap.getOrDefault("grandTotal", List.of("0")).get(0);
        grandTotal = grandTotalStr.isEmpty() ? 0f : Float.parseFloat(grandTotalStr);

        // Initializing the UI (See below!)
        initializeUI();
    }

    // VAADIN UI COMPONENTS
    // --------------------------------------------------------------------------------------------------------------------
    private void initializeUI() {

        // Defining the title and subtitle
        H1 title = new H1("Order Confirmation");
        H3 subtitle = new H3("Your pizza is in the oven!");

        // Defining the section to contain collapsible fields for the order details (see below for populating these details)
        H3 orderDetailsHeader = new H3("Order Details");
        Details crustSizeDetails = createCrustDetailsSection();
        Details meatDetails = createMeatDetailsSection();
        Details veggieDetails = createVeggieDetailsSection();

        // Creating the section for the pricing details
        H3 pricingDetailsHeader = new H3("Pricing Details");
        HorizontalLayout subtotalRow = createPricingRow("Subtotal:", String.format("$%.2f", subtotal));
        HorizontalLayout taxRow = createPricingRow("Tax:", String.format("$%.2f", subtotal * 0.1));
        HorizontalLayout tipRow = createPricingRow("Tip:", String.format("$%.2f", tipAmount));
        HorizontalLayout totalRow = createPricingRow("TOTAL:", String.format("$%.2f", grandTotal));

        // Defining the button to return to the order taker view to return to the order taker view
        Button orderAnotherPizzaButton = new Button("Order another pizza?", 
            e -> getUI().ifPresent(ui -> ui.navigate(OrderTakerView.class)));

        // Adding components to the overall layout
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().add(title, subtitle, new Hr(), orderDetailsHeader, crustSizeDetails, meatDetails, veggieDetails, new Hr(), pricingDetailsHeader, subtotalRow, taxRow, tipRow, totalRow, new Hr(), orderAnotherPizzaButton);
    }


    // HORIZONTAL VIEW LAYOUT DETAILS FOR DISPLAYING PRICING INFORMATION
    // --------------------------------------------------------------------------------------------------------------------
    // Defining the horizontal layout for the pricing details
    private HorizontalLayout createPricingRow(String label, String value) {
        HorizontalLayout row = new HorizontalLayout();
        row.setWidthFull();
        row.addClassName(Gap.MEDIUM);
        row.getStyle().set("flex-grow", "1");

        Paragraph labelParagraph = new Paragraph(label);
        labelParagraph.getStyle().set("flex-grow", "1");
        labelParagraph.setMinWidth("100px");
        labelParagraph.getStyle().set("font-size", "var(--lumo-font-size-xl)");

        Paragraph valueParagraph = new Paragraph(value);
        valueParagraph.setWidth("100%");
        valueParagraph.getStyle().set("font-size", "var(--lumo-font-size-xl)");

        row.add(labelParagraph, valueParagraph);
        return row;
    }



    // CRUST DETAILS FOR COLLAPSABLE FIELD
    // -------------------------------------------------------------------------------------------------------------------
    private void setDetailsCrustSize(Details details) {
        Span crustSizeSpan = new Span(this.crustSize);
        VerticalLayout content = new VerticalLayout(crustSizeSpan);
        content.setSpacing(false);
        content.setPadding(false);
        details.setSummaryText("Crust Size");
        details.add(content);
        details.setContent(content);
    }

    private Details createCrustDetailsSection() {
        Details details = new Details();
        details.setWidth("100%");
        setDetailsCrustSize(details);
        return details;
    }

    // MEAT DETAILS FOR COLLAPSABLE FIELD
    // -------------------------------------------------------------------------------------------------------------------
    private void setDetailsMeats(Details details) {
        VerticalLayout content = new VerticalLayout();
        this.meats.forEach(meat -> {
            Span meatSpan = new Span(meat);
            content.add(meatSpan);
        });
        details.setSummaryText("Meat Toppings");
        details.setOpened(false);
        details.setContent(content);
    }

    private Details createMeatDetailsSection() {
        Details details = new Details();
        details.setWidth("100%");
        setDetailsMeats(details);
        return details;
    }

    // VEGGIE DETAILS FOR COLLAPSABLE FIELD
    // -------------------------------------------------------------------------------------------------------------------
    private void setDetailsVeggies(Details details) {
        VerticalLayout content = new VerticalLayout();
        this.veggies.forEach(veggie -> {
            Span veggieSpan = new Span(veggie);
            content.add(veggieSpan);
        });
        details.setSummaryText("Veggie Toppings");
        details.setOpened(false);
        details.setContent(content);
    }

    private Details createVeggieDetailsSection() {
        Details details = new Details();
        details.setWidth("100%");
        setDetailsVeggies(details);
        return details;
    }
}
