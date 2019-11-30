package ru.protei.vaadin;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

import java.util.List;
import java.util.Map;

@Route(value = "account", layout = MainLayout.class)
public class AccountView extends Div implements HasUrlParameter<String> {

    private H1 textLabel;

    public AccountView() {
        H1 queryLabel = new H1("Мы посылаем .../app/filter/mkd/1/pbx//account/3000?id=100&name=Boris&age=45&sex=&department=7&department=3");
        textLabel = new H1("");
        add(new VerticalLayout(queryLabel, textLabel));
        setSizeFull();
    }

    @Override
    public void setParameter(BeforeEvent event, @WildcardParameter String parameter) {
        Location location = event.getLocation();
        QueryParameters queryParameters = location.getQueryParameters();
        Map<String, List<String>> parametersMap = queryParameters.getParameters();
        textLabel.setText("Мы приняли и распарсили: " + parameter + " и " + parametersMap.toString());
    }
}
