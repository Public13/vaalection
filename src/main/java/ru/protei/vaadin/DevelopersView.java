package ru.protei.vaadin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.protei.backend.service.DevelopersService;
import ru.protei.model.Developer;

@PageTitle("Vaadin App::Developers")
@Route(value = "", layout = MainLayout.class)
public class DevelopersView extends Div {

    public DevelopersView(@Autowired DevelopersService developersService) {

        Grid<Developer> developerGrid = new Grid<>();
        developerGrid.setId("developer-grid");
        developerGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        developerGrid.setHeightFull();
        developerGrid.addColumn(Developer::getFirstname).setHeader("First name");
        developerGrid.addColumn(Developer::getLastname).setHeader("Last name");
        developerGrid.addColumn(Developer::getEmail).setHeader("Email");
        developerGrid.setItems(developersService.getDevelopers());
        developerGrid.asSingleSelect().addValueChangeListener(
                event -> UI.getCurrent().navigate(DevelopersEditView.class, event.getValue().getId()));

        add(developerGrid);
        setSizeFull();
    }
}
