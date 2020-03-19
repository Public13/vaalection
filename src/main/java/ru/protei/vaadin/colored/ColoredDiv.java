package ru.protei.vaadin.colored;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.templatemodel.TemplateModel;

@Tag("colored-div")
@JsModule("./src/colored-div.js")
public class ColoredDiv extends PolymerTemplate<ColoredDiv.ColoredModel> {

    public interface ColoredModel extends TemplateModel {
    }
}

