package ru.protei.vaadin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.validator.RegexpValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import org.springframework.beans.factory.annotation.Autowired;
import ru.protei.backend.service.DevelopersService;
import ru.protei.model.Developer;

@Route(value = "developerEdit", layout = MainLayout.class)
@RouteAlias(value = "edit", layout = MainLayout.class)
public class DevelopersEditView extends Div implements HasUrlParameter<Integer> {
    private transient DevelopersService developersService;

    private Binder<Developer> binder;
    private transient Developer developerToEdit;

    private TextField firstName = new TextField();
    private TextField lastName = new TextField();
    private TextField email = new TextField();
    private PasswordField password = new PasswordField();

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    @Autowired
    public DevelopersEditView(DevelopersService developersService) {
        someInit(developersService);

        Label emailStatus = new Label();
        emailStatus.getStyle().set("color", "Red");

        binder = new Binder<>(Developer.class);

        binder.forMemberField(firstName)
                .asRequired()
                .withValidator(new RegexpValidator(
                        "Напишите имя как в паспорте",
                        "[a-zA-Zа-яА-Я]*"));

        binder.forMemberField(email)
                .withValidator(new EmailValidator("Ваш емаил какой-то не очень"))
                .withStatusLabel(emailStatus);

        binder.bindInstanceFields(this);

        Div editorLayout = createEditorLayout();

        add(emailStatus, editorLayout);
        setHeightFull();
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Integer developerId) {
        developerToEdit = developersService.getDeveloperById(developerId);
        binder.readBean(developerToEdit);
        password.setValue("");
    }

    private void updateDeveloper() {
        try {

            binder.writeBean(developerToEdit);

            if (developersService.save(developerToEdit)) {
                Notification.show("Updated");
            } else {
                Notification.show("ERROR!!! ALARM!!");
            }
        } catch (ValidationException e) {
            System.out.println("oh...");
        }
    }

    private Div createEditorLayout() {
        Div editorDiv = new Div();
        editorDiv.setId("editor-layout");

        FormLayout formLayout = new FormLayout();
        formLayout.addFormItem(firstName, "Имя");
        formLayout.addFormItem(lastName, "Фамилия");
        formLayout.addFormItem(email, "Email");
        formLayout.addFormItem(password, "Пароль");
        formLayout.getChildren().forEach(component -> component.getElement().getClassList().add("full-width"));

        HorizontalLayout buttonLayout = createButtonLayout();

        editorDiv.add(formLayout, buttonLayout);

        return editorDiv;
    }

    private HorizontalLayout createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setId("button-layout");
        buttonLayout.setWidthFull();
        buttonLayout.setSpacing(true);

        cancel.addClickListener(e -> UI.getCurrent().navigate(""));
        save.addClickListener(e -> updateDeveloper());
        cancel.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonLayout.add(cancel, save);
        return buttonLayout;
    }

    private void someInit(DevelopersService developersService) {
        this.developersService = developersService;
        firstName.getElement().getClassList().add("full-width");
        firstName.addValueChangeListener( e -> binder.validate());
        lastName.getElement().getClassList().add("full-width");
        email.getElement().getClassList().add("full-width");
        password.getElement().getClassList().add("full-width");
    }
}
