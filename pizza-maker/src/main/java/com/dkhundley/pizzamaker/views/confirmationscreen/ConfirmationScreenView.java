package com.dkhundley.pizzamaker.views.confirmationscreen;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;

@PageTitle("Confirmation Screen")
@Route("confirmation-screen")
public class ConfirmationScreenView extends Composite<VerticalLayout> {

    public ConfirmationScreenView() {
        H1 h1 = new H1();
        H3 h3 = new H3();
        Hr hr = new Hr();
        H3 h32 = new H3();
        Details details = new Details();
        Details details2 = new Details();
        Details details3 = new Details();
        Hr hr2 = new Hr();
        H3 h33 = new H3();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Paragraph textLarge = new Paragraph();
        Paragraph textLarge2 = new Paragraph();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Paragraph textLarge3 = new Paragraph();
        Paragraph textLarge4 = new Paragraph();
        HorizontalLayout layoutRow3 = new HorizontalLayout();
        Paragraph textLarge5 = new Paragraph();
        Paragraph textLarge6 = new Paragraph();
        HorizontalLayout layoutRow4 = new HorizontalLayout();
        Paragraph textLarge7 = new Paragraph();
        Paragraph textLarge8 = new Paragraph();
        Hr hr3 = new Hr();
        Button buttonSecondary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h1.setText("Order Confirmation");
        h1.setWidth("max-content");
        h3.setText("Your pizza is in the oven!");
        h3.setWidth("max-content");
        h32.setText("Order Details");
        h32.setWidth("max-content");
        details.setWidth("100%");
        setDetailsSampleData(details);
        details2.setWidth("100%");
        setDetailsSampleData(details2);
        details3.setWidth("100%");
        setDetailsSampleData(details3);
        h33.setText("Pricing Details");
        h33.setWidth("max-content");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        textLarge.setText("Subtotal:");
        textLarge.getStyle().set("flex-grow", "1");
        textLarge.setMinWidth("100px");
        textLarge.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        textLarge2.setText(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        textLarge2.setWidth("100%");
        textLarge2.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        layoutRow2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");
        textLarge3.setText("Tax:");
        textLarge3.getStyle().set("flex-grow", "1");
        textLarge3.setMinWidth("100px");
        textLarge3.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        textLarge4.setText(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        textLarge4.setWidth("100%");
        textLarge4.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        layoutRow3.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");
        textLarge5.setText("Tip:");
        textLarge5.getStyle().set("flex-grow", "1");
        textLarge5.setMinWidth("100px");
        textLarge5.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        textLarge6.setText(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        textLarge6.setWidth("100%");
        textLarge6.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        layoutRow4.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow4);
        layoutRow4.addClassName(Gap.MEDIUM);
        layoutRow4.setWidth("100%");
        layoutRow4.getStyle().set("flex-grow", "1");
        textLarge7.setText("TOTAL:");
        textLarge7.getStyle().set("flex-grow", "1");
        textLarge7.setMinWidth("100px");
        textLarge7.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        textLarge8.setText(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.");
        textLarge8.setWidth("100%");
        textLarge8.getStyle().set("font-size", "var(--lumo-font-size-xl)");
        buttonSecondary.setText("Order another pizza?");
        buttonSecondary.setWidth("min-content");
        getContent().add(h1);
        getContent().add(h3);
        getContent().add(hr);
        getContent().add(h32);
        getContent().add(details);
        getContent().add(details2);
        getContent().add(details3);
        getContent().add(hr2);
        getContent().add(h33);
        getContent().add(layoutRow);
        layoutRow.add(textLarge);
        layoutRow.add(textLarge2);
        getContent().add(layoutRow2);
        layoutRow2.add(textLarge3);
        layoutRow2.add(textLarge4);
        getContent().add(layoutRow3);
        layoutRow3.add(textLarge5);
        layoutRow3.add(textLarge6);
        getContent().add(layoutRow4);
        layoutRow4.add(textLarge7);
        layoutRow4.add(textLarge8);
        getContent().add(hr3);
        getContent().add(buttonSecondary);
    }

    private void setDetailsSampleData(Details details) {
        Span name = new Span("Sophia Williams");
        Span email = new Span("sophia.williams@company.com");
        Span phone = new Span("(501) 555-9128");
        VerticalLayout content = new VerticalLayout(name, email, phone);
        content.setSpacing(false);
        content.setPadding(false);
        details.setSummaryText("Contact information");
        details.setOpened(true);
        details.setContent(content);
    }
}
