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

@CssImport("./styles/views/main/main-layout.css")
public class MainLayout extends VerticalLayout implements RouterLayout {

    private Div content;

    public MainLayout() {

        H2 header = new H2("Приложение VaadinApp");
        header.setId("app-header");
        add(header);

        Tabs tabs = new Tabs();
        Tab developersTab = new Tab(new RouterLink("Разработчики" , DevelopersView.class));
        Tab departmentsTab = new Tab(new RouterLink("Отделы" , DepartmentsView.class));
        Tab templatesTab = new Tab(new RouterLink("Шаблоны" , TemplatesView.class));

        RouterLink wildcardLink = new RouterLink("Profile wildcard", ProfileView.class, "mkd/205/pbx/1/account/3000/edit");
        Tab wildcardTab = new Tab(wildcardLink);

        Map<String, List<String>> queryMap = new HashMap<>();
        queryMap.put("mkd", Collections.singletonList("205"));
        queryMap.put("pbx", Collections.singletonList("1"));
        queryMap.put("profile", Collections.singletonList("3000"));

        RouterLink queryLink = new RouterLink("Profile query", ProfileQueryView.class);
        queryLink.setQueryParameters(new QueryParameters(queryMap));
        Tab queryTab = new Tab(queryLink);

        tabs.add(developersTab, departmentsTab, templatesTab, wildcardTab, queryTab);

        content = new Div();
        content.setId("main-content");
        content.setSizeFull();
        add(tabs, content, new H4("Подвал.. http://protei.ru"));
        setSizeFull();
    }

    @Override
    public void showRouterLayoutContent(HasElement element) {
        content.add((Component) element);
    }
}
