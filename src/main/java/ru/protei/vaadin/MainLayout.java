package ru.protei.vaadin;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasElement;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.QueryParameters;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;

import java.util.*;

@CssImport("styles/views/main/main-layout.css")
public class MainLayout extends VerticalLayout implements RouterLayout {

    private Div content;

    public MainLayout() {

        H2 header = new H2("Приложение VaadinApp");
        header.setId("app-header");
        add(header);

        Tabs tabs = new Tabs();
        Tab developersTab = new Tab(new RouterLink("Разработчики" , DevelopersView.class));
        Tab departmentsTab = new Tab(new RouterLink("Отделы" , DepartmentsView.class));

        Map<String, List<String>> queryMap = new HashMap<>();
        queryMap.put("id", Collections.singletonList("100"));
        queryMap.put("name", Collections.singletonList("Boris"));
        queryMap.put("age", Collections.singletonList("45"));
        queryMap.put("sex", Collections.singletonList(""));
        queryMap.put("department", Arrays.asList("7","3"));

        RouterLink queryLink = new RouterLink("Вьюха с Query", QueryView.class);
        queryLink.setQueryParameters(new QueryParameters(queryMap));
        Tab queryTab = new Tab(queryLink);

        RouterLink wildcardLink = new RouterLink("Вьюха с параметрами и Query", QueryView.class, "mkd/1/pbx//account/3000");
        wildcardLink.setQueryParameters(new QueryParameters(queryMap));
        Tab wildcardTab = new Tab(wildcardLink);

        tabs.add(developersTab, departmentsTab, queryTab, wildcardTab);

        content = new Div();
        content.setId("main-content");
        content.setSizeFull();
        add(tabs, content, new H4("Подвал.. смотри как умею"));
        setSizeFull();
    }

    @Override
    public void showRouterLayoutContent(HasElement element) {
        content.add((Component) element);
    }
}
