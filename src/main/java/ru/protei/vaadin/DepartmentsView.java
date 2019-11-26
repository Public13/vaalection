package ru.protei.vaadin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import ru.protei.backend.service.DepartmentsService;
import ru.protei.model.Department;

@PageTitle("Vaadin App::Departments")
@CssImport("styles/views/main/main-view.css")
@Route(value = "departments", layout = MainLayout.class)
public class DepartmentsView extends Div implements AfterNavigationObserver {

    @Autowired
    public DepartmentsView(DepartmentsService departmentsService) {

        Grid<Department> departmentsGrid = new Grid<>(Department.class);
        departmentsGrid.setId("departments-grid");
        departmentsGrid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        departmentsGrid.setHeightFull();
        departmentsGrid.setItems(departmentsService.getDepartments());

        add(departmentsGrid);
        setSizeFull();
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        System.out.println("====== DEPARTMENTS UI "+ UI.getCurrent().toString());
    }
}
