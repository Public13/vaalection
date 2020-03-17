package ru.protei.vaadin.mkdservice;

import com.vaadin.flow.templatemodel.TemplateModel;

public interface MkdServiceModel extends TemplateModel {
    String getNumber();
    String getDate();
    void setCardCaption(String caption);
}
