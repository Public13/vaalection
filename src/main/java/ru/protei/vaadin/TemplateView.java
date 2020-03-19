package ru.protei.vaadin;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.EventHandler;
import com.vaadin.flow.component.polymertemplate.Id;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.component.polymertemplate.RepeatIndex;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.templatemodel.Include;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import ru.protei.backend.service.DevelopersService;
import ru.protei.model.Developer;
import ru.protei.vaadin.mkdservice.MkdService;

import java.util.List;

@PageTitle("Vaadin App::Templates")
@Tag("template-view")
@JsModule("./src/template-view.js")
@Route(value = "templates", layout = MainLayout.class)
public class TemplateView extends PolymerTemplate<TemplateView.TemplateViewModel> {

    @Id("service1")
    private MkdService service1;

    @Id("service2")
    private MkdService service2;

    @Autowired
    public TemplateView(DevelopersService service) {
        setId("template-view");
        setDeveloperList(service.getDevelopers());
    }

    @EventHandler
    public void handleClick(@RepeatIndex int itemIndex) {
        Developer developer = getDeveloperList().get(itemIndex);
        System.out.println(developer.getFirstname());
    }

    public List<Developer> getDeveloperList() {
        return getModel().getDevelopers();
    }

    public void setDeveloperList(List<Developer> developerList) {
        getModel().setDevelopers(developerList);
    }

    public interface TemplateViewModel extends TemplateModel {
        List<Developer> getDevelopers();
        @Include({ "firstname", "lastname", "email" })
        void setDevelopers(List<Developer> developers);
    }
}

