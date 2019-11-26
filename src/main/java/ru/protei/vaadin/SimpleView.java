package ru.protei.vaadin;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;
import com.vaadin.flow.router.Route;

@Route(value = "simple")
public class SimpleView extends Div implements HasUrlParameter<String> {

    private H1 h1 = new H1("Просто текст");

    public SimpleView() {
        add(h1);
        setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        if (parameter != null) {
            h1.setText("Просто текст от " + parameter);
        }
    }
}
