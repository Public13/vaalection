package ru.protei.vaadin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;
import ru.protei.backend.service.DevelopersService;
import ru.protei.model.Developer;

@Route(value = "", layout = MainLayout.class)
@PWA(name = "Project Base for Vaadin Flow with Spring", shortName = "Vaalection")
@PageTitle("Vaadin App")
@CssImport("styles/views/main/main-view.css")
public class DevelopersView extends Div implements AfterNavigationObserver {

    private Grid<Developer> developerGrid;

    @Autowired
    public DevelopersView(DevelopersService developersService) {

        System.out.println("====== MAIN constructor");

        developerGrid = new Grid<>();
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

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        System.out.println("====== MAIN UI "+ UI.getCurrent().toString());
    }
}
