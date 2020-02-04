package ru.protei.vaadin;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.List;
import java.util.Map;

@Route(value = "profile", layout = MainLayout.class)
public class ProfileView extends Div implements HasUrlParameter<String> {

    private H1 textLabel;
    public ProfileView() {
        H1 queryLabel = new H1("Мы посылаем .../profile/mkd/205/pbx/1/account/3000/edit");
        textLabel = new H1("");
        add(new VerticalLayout(queryLabel, textLabel));
        setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent event, @WildcardParameter String parameter) {
        textLabel.setText("Мы приняли " + parameter);
    }
}
