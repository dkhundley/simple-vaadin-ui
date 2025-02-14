// Importing all the special code we need to build our Vaadin UI
package com.dkhundley.pizzamaker.views.confirmationscreen;

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

import java.util.List;
import java.util.Map;

// Setting our page route (Note: if this was empty, it would be considered the root or "home" page. Naturally, this is not our home page!)
@PageTitle("Confirmation Screen")
@Route("confirmation-screen")
public class ConfirmationScreenView extends Composite<VerticalLayout> implements HasUrlParameter<String> {

    // Constructing the view
    public ConfirmationScreenView() {
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
    }

    // #X. GETTING THE ORDER DETAILS FROM THE ORDER TAKER SCREEN!!!
    // -------------------------------------------------------------------------------------------------------------------
    // Instantiating variables to store order details
    private String crustSize;
    private List<String> meats;
    private List<String> veggies;
    private String tipPercentage;
    private Float customTipAmount;
    private Float crustSizeValue;
    private List<Float> meatsValues;
    private List<Float> veggiesValues;

    // Setting the parameters from the pizza order taker view
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        
        // Extracting the values from the previous order taker view
        QueryParameters queryParameters = event.getLocation().getQueryParameters();
        Map<String, List<String>> parameterMap = queryParameters.getParameters();
        
        // Storing values in instance variables
        crustSize = parameterMap.getOrDefault("crustSizeLabel", List.of("")).get(0);
        meats = List.of(parameterMap.getOrDefault("meatsLabels", List.of("")).get(0).split(","));
        veggies = List.of(parameterMap.getOrDefault("veggiesLabels", List.of("")).get(0).split(","));
        tipPercentage = parameterMap.getOrDefault("tipPercentage", List.of("")).get(0);
        
        // Parse numeric values safely
        String customTipStr = parameterMap.getOrDefault("customTipAmount", List.of("0")).get(0);
        customTipAmount = customTipStr.isEmpty() ? 0f : Float.parseFloat(customTipStr);
        
        crustSizeValue = Float.parseFloat(parameterMap.getOrDefault("crustSizeValue", List.of("0")).get(0));
        
        // Parse arrays of float values
        String meatsValuesStr = parameterMap.getOrDefault("meatsValues", List.of("")).get(0);
        meatsValues = meatsValuesStr.isEmpty() ? 
            List.of() : 
            List.of(meatsValuesStr.split(",")).stream()
                .map(Float::parseFloat)
                .toList();
        
        String veggiesValuesStr = parameterMap.getOrDefault("veggiesValues", List.of("")).get(0);
        veggiesValues = veggiesValuesStr.isEmpty() ? 
            List.of() : 
            List.of(veggiesValuesStr.split(",")).stream()
                .map(Float::parseFloat)
                .toList();

        // Initializing the UI (See below!)
        initializeUI();
    }

    // #X. DEFINING THE VIEW OF THE ORDER RESULTS SCREEN
    // -------------------------------------------------------------------------------------------------------------------
    private void initializeUI() {

        // Page title and subtitle
        H1 title = new H1("Order Confirmation");
        H3 subtitle = new H3("Your pizza is in the oven!");

        // Order details section
        H3 orderDetailsHeader = new H3("Order Details");
        Details crustSizeDetails = createCrustDetailsSection();
        Details meatDetails = createMeatDetailsSection();
        Details veggieDetails = createVeggieDetailsSection();

        // Pricing details section
        H3 pricingDetailsHeader = new H3("Pricing Details");
        HorizontalLayout subtotalRow = createPricingRow("Subtotal:", "$0.00");
        HorizontalLayout taxRow = createPricingRow("Tax:", "$0.00");
        HorizontalLayout tipRow = createTipRow("Tip:", "$0.00");
        HorizontalLayout totalRow = createPricingRow("TOTAL:", "$0.00");

        // Secondary action button
        Button orderAnotherPizzaButton = new Button("Order another pizza?");

        // Adding components to the overall layout
        getContent().add(
            title, 
            subtitle, 
            new Hr(), 
            orderDetailsHeader, 
            crustSizeDetails, 
            meatDetails, 
            veggieDetails, 
            new Hr(), 
            pricingDetailsHeader, 
            subtotalRow, 
            taxRow, 
            tipRow, 
            totalRow, 
            new Hr(), 
            orderAnotherPizzaButton
        );
    }

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

    private HorizontalLayout createTipRow(String label, String value) {
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



    // SETTING CRUST DETAILS
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

    // SETTING MEAT TOPPING DETAILS
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

    // SETTING VEGGIE TOPPING DETAILS
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
