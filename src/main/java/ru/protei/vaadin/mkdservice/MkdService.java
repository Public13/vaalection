package ru.protei.vaadin.mkdservice;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;

@Tag("mkd-service")
@NpmPackage(value = "@polymer/paper-input", version = "3.0.2")
@JsModule("./src/mkd-service.js")
@CssImport(value = "./styles/components/mkd-service/bootstrap.css", id = "bootstrap")
@CssImport(value = "./styles/components/mkd-service/mkd-service.css", id = "mkd-service-style")
@CssImport(value = "./styles/components/mkd-service/julia.css", id = "julia")
public class MkdService extends PolymerTemplate<MkdServiceModel> {
    private static final String EMPTY_NAME_GREETING = "Please enter your name";

    public MkdService() {
        setId("template");
        getModel().setGreeting(EMPTY_NAME_GREETING);
    }

    @EventHandler
    private void sayHello() {
        String userInput = getModel().getUserInput();
        if (userInput == null || userInput.isEmpty()) {
            getModel().setGreeting(EMPTY_NAME_GREETING);
        } else {
            getModel().setGreeting(String.format("Hello %s!", userInput));
        }
    }
}

