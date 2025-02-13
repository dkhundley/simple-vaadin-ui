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

@PageTitle("Order Taker")
@Route("")
public class OrderTakerView extends Composite<VerticalLayout> {

    public OrderTakerView() {
        H1 h1 = new H1();
        H2 h2 = new H2();
        Hr hr = new Hr();
        H3 h3 = new H3();
        FormLayout formLayout3Col = new FormLayout();
        Select select = new Select();
        MultiSelectComboBox multiSelectComboBox = new MultiSelectComboBox();
        MultiSelectComboBox multiSelectComboBox2 = new MultiSelectComboBox();
        Hr hr2 = new Hr();
        H3 h32 = new H3();
        RadioButtonGroup radioGroup = new RadioButtonGroup();
        NumberField numberField = new NumberField();
        Button buttonPrimary = new Button();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        h1.setText("Pizza Maker");
        h1.setWidth("max-content");
        h2.setText("Create your pizza below!");
        h2.setWidth("max-content");
        h3.setText("Select Your Toppings");
        h3.setWidth("max-content");
        formLayout3Col.setWidth("100%");
        formLayout3Col.setResponsiveSteps(new ResponsiveStep("0", 1), new ResponsiveStep("250px", 2),
                new ResponsiveStep("500px", 3));
        select.setLabel("Crust Size");
        select.setWidth("min-content");
        setSelectSampleData(select);
        multiSelectComboBox.setLabel("Meats");
        multiSelectComboBox.setWidth("min-content");
        setMultiSelectComboBoxSampleData(multiSelectComboBox);
        multiSelectComboBox2.setLabel("Veggies");
        multiSelectComboBox2.setWidth("min-content");
        setMultiSelectComboBoxSampleData(multiSelectComboBox2);
        h32.setText("Add a Tip?");
        h32.setWidth("max-content");
        radioGroup.setLabel("Percentage");
        radioGroup.setWidth("min-content");
        radioGroup.setItems("Order ID", "Product Name", "Customer", "Status");
        radioGroup.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        numberField.setLabel("Custom Amount");
        numberField.setWidth("min-content");
        buttonPrimary.setText("Submit Order!");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        getContent().add(h1);
        getContent().add(h2);
        getContent().add(hr);
        getContent().add(h3);
        getContent().add(formLayout3Col);
        formLayout3Col.add(select);
        formLayout3Col.add(multiSelectComboBox);
        formLayout3Col.add(multiSelectComboBox2);
        getContent().add(hr2);
        getContent().add(h32);
        getContent().add(radioGroup);
        getContent().add(numberField);
        getContent().add(buttonPrimary);
    }

    record SampleItem(String value, String label, Boolean disabled) {
    }

    private void setSelectSampleData(Select select) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        select.setItems(sampleItems);
        select.setItemLabelGenerator(item -> ((SampleItem) item).label());
        select.setItemEnabledProvider(item -> !Boolean.TRUE.equals(((SampleItem) item).disabled()));
    }

    private void setMultiSelectComboBoxSampleData(MultiSelectComboBox multiSelectComboBox) {
        List<SampleItem> sampleItems = new ArrayList<>();
        sampleItems.add(new SampleItem("first", "First", null));
        sampleItems.add(new SampleItem("second", "Second", null));
        sampleItems.add(new SampleItem("third", "Third", Boolean.TRUE));
        sampleItems.add(new SampleItem("fourth", "Fourth", null));
        multiSelectComboBox.setItems(sampleItems);
        multiSelectComboBox.setItemLabelGenerator(item -> ((SampleItem) item).label());
    }
}
