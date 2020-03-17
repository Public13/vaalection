package ru.protei.vaadin;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.TemplateModel;
import ru.protei.vaadin.mkdservice.MkdService;

@PageTitle("Vaadin App::Departments")
@Tag("template-view")
@JsModule("./src/template-view.js")
@CssImport("./styles/views/main/main-view.css")
@Route(value = "templates", layout = MainLayout.class)
public class TemplateView extends PolymerTemplate<TemplateView.TemplateViewModel> {

    @Id("service1")
    private MkdService service1;

    @Id("service2")
    private MkdService service2;

    public TemplateView() {
        setId("template-view");
    }

    public interface TemplateViewModel extends TemplateModel {
    }
}

