package ru.protei.vaadin.mkdservice;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;

@Tag("mkd-service")
@NpmPackage(value = "@polymer/paper-input", version = "3.0.2")
//@NpmPackage(value = "js-datepicker", version = "5.2.1")
@JsModule("./src/mkd-service.js")
@JavaScript("./src/jquery-3.4.1.min.js")
@JavaScript("./src/datepicker.js")
@JavaScript("./src/test.js")
public class MkdService extends PolymerTemplate<MkdServiceModel> implements AfterNavigationObserver {
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
        UI.getCurrent().getPage().executeJs(
                "console.log('try to datepick'); $('#datepicker-here').css('background-color','red');"
        );
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        UI.getCurrent().getPage().addJavaScript("src/test.js");
        UI.getCurrent().getPage().addJavaScript("src/jquery-3.4.1.min.js");
        UI.getCurrent().getPage().addJavaScript("src/datepicker.js");
        UI.getCurrent().getPage().addJavaScript("src/datepicker.en.js");
    }
}

