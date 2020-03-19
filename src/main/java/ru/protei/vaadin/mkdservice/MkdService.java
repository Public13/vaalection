package ru.protei.vaadin.mkdservice;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("mkd-service")
@NpmPackage(value = "@polymer/paper-input", version = "3.0.2")
@JsModule("./src/mkd-service.js")
@CssImport("./styles/font-awesome/css/font-awesome.css")
@CssImport(value = "./styles/font-awesome/css/font-awesome.css", id = "font-awesome")
@CssImport(value = "./styles/components/mkd-service/julia.css", id = "julia")
@CssImport(value = "./styles/components/mkd-service/dialog-overlay-theme.css", themeFor = "vaadin-dialog-overlay")
public class MkdService extends PolymerTemplate<MkdService.MkdServiceModel> {
    private static final String EMPTY_NAME_GREETING = "Please enter service number";

    @Id("dialog")
    private Dialog dialog;

    public MkdService() {
        getModel().setCardCaption(EMPTY_NAME_GREETING);
        getElement().addPropertyChangeListener("number", event ->
                System.out.println("Number is set to: " + getModel().getNumber()));
    }

    @EventHandler
    private void applyChanges() {
        String date = getModel().getDate();
        String numberInput = getModel().getNumber();
        if (numberInput == null || numberInput.isEmpty()) {
            getModel().setCardCaption(EMPTY_NAME_GREETING);
        } else {
            getModel().setCardCaption(String.format("%s triggered on %s", numberInput, formatDate(date)));
        }
        dialog.close();
    }

    public interface MkdServiceModel extends TemplateModel {
        String getNumber();

        String getDate();

        void setCardCaption(String caption);
    }

    private String formatDate(String date) {
        return date.substring(0, date.indexOf('T'));
    }
}

