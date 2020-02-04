package ru.protei.vaadin.mkdservice;

import com.vaadin.flow.templatemodel.TemplateModel;

public interface MkdServiceModel extends TemplateModel {
    String getUserInput();
    void setGreeting(String greeting);
}
