package ru.protei.vaadin;

import com.vaadin.flow.component.AbstractField;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;
import ru.protei.backend.service.DevelopersService;
import ru.protei.model.Developer;

@Route
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Vaalection")
@PageTitle("Vaadin App")
@CssImport("styles/views/main/main-view.css")
public class MainView extends Div {

    private transient DevelopersService employeesService;
    private Binder<Developer> binder;
    private transient Developer selectedEmployee;

    private Grid<Developer> developerGrid;

    private TextField firstName = new TextField();
    private TextField lastName = new TextField();
    private TextField email = new TextField();
    private PasswordField password = new PasswordField();

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    @Autowired
    public MainView(DevelopersService employeesService) {
        doSomeInit(employeesService);

        developerGrid = new Grid<>();
        developerGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        developerGrid.setHeightFull();
        developerGrid.addColumn(Developer::getFirstname).setHeader("First name");
        developerGrid.addColumn(Developer::getLastname).setHeader("Last name");
        developerGrid.addColumn(Developer::getEmail).setHeader("Email");

        developerGrid.setItems(employeesService.getDevelopers());

        developerGrid.asSingleSelect().addValueChangeListener(
                event -> populateForm(event.getValue()));

        Div gridLayout = createGridLayout(developerGrid);
        Div editorLayout = createEditorLayout();

        SplitLayout splitLayout = new SplitLayout();
        splitLayout.setSizeFull();
        splitLayout.addToPrimary(gridLayout);
        splitLayout.addToSecondary(editorLayout);

        add(splitLayout);
        setHeightFull();
    }

    private Div createEditorLayout() {
        Div editorDiv = new Div();
        editorDiv.setId("editor-layout");
        FormLayout formLayout = new FormLayout();
        addFormItem(editorDiv, formLayout, firstName, "First name");
        addFormItem(editorDiv, formLayout, lastName, "Last name");
        addFormItem(editorDiv, formLayout, email, "Email");
        addFormItem(editorDiv, formLayout, password, "Password");

        HorizontalLayout buttonLayout = createButtonLayout();
        editorDiv.add(buttonLayout);

        return editorDiv;
    }

    private HorizontalLayout createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);

        cancel.addClickListener(e -> developerGrid.asSingleSelect().clear());
        save.addClickListener(e -> updateEmployee());
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonLayout.add(cancel, save);
        return buttonLayout;
    }

    private Div createGridLayout(Component grid) {
        Div wrapper = new Div();
        wrapper.setId("wrapper");
        wrapper.setWidthFull();
        wrapper.add(grid);
        return wrapper;
    }

    private void doSomeInit(DevelopersService employeesService) {
        this.employeesService = employeesService;
        binder = new Binder<>(Developer.class);
        binder.bindInstanceFields(this);
    }

    private void addFormItem(Div wrapper, FormLayout formLayout, AbstractField field, String fieldName) {
        formLayout.addFormItem(field, fieldName);
        wrapper.add(formLayout);
        field.getElement().getClassList().add("full-width");
    }

    private void updateEmployee() {
        try {
            binder.writeBean(selectedEmployee);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        catch (Exception ignore) {
        }
        if (employeesService.save(selectedEmployee)) {
            developerGrid.setItems(employeesService.getDevelopers());
            Notification.show("Updated");
        } else {
            Notification.show("ERROR!!! ALARM!!");
        }
    }

    private void populateForm(Developer value) {
        selectedEmployee = value;
        binder.readBean(value);
        password.setValue("");
    }
}
