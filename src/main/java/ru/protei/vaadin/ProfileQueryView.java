package ru.protei.vaadin;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.List;
import java.util.Map;

@Route(value = "profile", layout = MainLayout.class)
public class ProfileQueryView extends Div implements HasUrlParameter<String> {

    private H1 textLabel;
    public ProfileQueryView() {
        H1 queryLabel = new H1("Мы посылаем ...profile?mkd=205&pbx=1&profile=3000&method=edit");
        textLabel = new H1("");
        add(new VerticalLayout(queryLabel, textLabel));
        setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter String parameter) {
        Location location = event.getLocation();
        Map<String, List<String>> parametersMap =
                location.getQueryParameters().getParameters();
        textLabel.setText("Мы приняли и распарсили: " + parametersMap.toString());
    }
}
