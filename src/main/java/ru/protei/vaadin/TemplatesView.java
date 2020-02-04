package ru.protei.vaadin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import ru.protei.vaadin.mkdservice.MkdService;

@PageTitle("Vaadin App::Departments")
@CssImport("./styles/views/main/main-view.css")
//@JavaScript("./js/test.js")
@Route(value = "templates", layout = MainLayout.class)
public class TemplatesView extends Div implements AfterNavigationObserver {

    public TemplatesView() {
        MkdService mkdService = new MkdService();
        add(mkdService);
        setSizeFull();
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
//        UI.getCurrent().getPage()
//                .addJavaScript("./js/test.js");
//        UI.getCurrent().getPage().executeJs(
//                "console.log('from main template'); scrim();"
//        );
    }
}
