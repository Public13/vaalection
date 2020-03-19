package ru.protei.vaadin;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.protei.backend.service.DevelopersService;

@PageTitle("Vaadin App::Container")
@Route(value = "container", layout = MainLayout.class)
public class ContainsDevelopersView extends Div {

    public ContainsDevelopersView(@Autowired DevelopersService developersService) {

        Label infoLabel = new Label("Это текст контейнера, далее идет использование другой вьюхи:");

        DevelopersView developersView = new DevelopersView(developersService);
        add(infoLabel, developersView);
        setSizeFull();
    }
}
