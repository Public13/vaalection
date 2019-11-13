package ru.protei.backend.dao;

import org.springframework.stereotype.Service;
import ru.protei.model.Developer;

import java.util.ArrayList;
import java.util.List;

@Service
public class DevelopersDao {

    private List<Developer> developers;

    {
        developers = new ArrayList<>();
        developers.add(new Developer(1,"Надя", "Leeming", "rleeming0@bbc.co.uk", "Food Chemist"));
        developers.add(new Developer(2,"Маша", "Delong", "adelong1@altervista.org", "Recruiting Manager"));
        developers.add(new Developer(3,"Леша", "Burry", "lburry2@example.com", "Food Chemist"));
        developers.add(new Developer(4,"Денис", "Oaten", "koaten3@ihg.com", "VP Sales"));
        developers.add(new Developer(5,"Павел", "Huke", "mhuke4@washingtonpost.com", "Research Assistant IV"));
        developers.add(new Developer(6,"Вова", "Widdowes", "gwiddowes5@cargocollective.com", "Actuary"));
        developers.add(new Developer(7,"Леша", "Roadknight", "droadknight6@apache.org", "Mechanical Systems Engineer"));
        developers.add(new Developer(8,"Никита", "Nowland", "tnowland7@biblegateway.com", "Senior Developer"));
        developers.add(new Developer(9,"Саша", "Teresia", "tteresia8@boston.com", "Assistant Manager"));
        developers.add(new Developer(10,"Вова", "Yon", "syon9@ocn.ne.jp", "Senior Sales Associate"));
        developers.add(new Developer(11,"Юля", "Willes", "cwillesa@linkedin.com", "Programmer I"));
        developers.add(new Developer(12,"Александр", "Lambertz", "jlambertzb@nymag.com", "Operator"));
        developers.add(new Developer(13,"Илья", "Loker", "olokerc@gov.uk", "Developer I"));
        developers.add(new Developer(14,"Иннокентий", "Shawell", "bshawelld@posterous.com", "Research Assistant IV"));
        developers.add(new Developer(15,"Ипполит", "Mainston", "tmainstone@cmu.edu", "Research Nurse"));
        developers.add(new Developer(16,"Фрося", "Gehring", "tgehringf@a8.net", "Geological Engineer"));
        developers.add(new Developer(17,"Роман", "Pionter", "apionterg@ehow.com", "Senior Financial Analyst"));
        developers.add(new Developer(18,"Олег", "Aveson", "mavesonh@shop-pro.jp", "Technical Writer"));
        developers.add(new Developer(19,"Сеня", "Moreby", "jmorebyi@slashdot.org", "Executive Secretary"));
        developers.add(new Developer(20,"Михаил", "Bangley", "gbangleyj@prlog.org", "Account Executive"));
        developers.add(new Developer(21,"Андрей", "Glave", "iglavek@tamu.edu", "Compensation Analyst"));
        developers.add(new Developer(22,"Екатерина", "Spatarul", "cspatarull@sun.com", "Business Systems Development Analyst"));
    }

    public List<Developer> getAll() {
        return developers;
    }

    public Developer findById(int id) {
        return developers.stream().filter(e -> e.getId()==id).findFirst().orElse(null);
    }

    public void update(Developer employee) {
        Developer findDeveloper = findById(employee.getId());
        if(findDeveloper!=null) {
            findDeveloper.setEmail(employee.getEmail());
            findDeveloper.setFirstname(employee.getFirstname());
            findDeveloper.setLastname(employee.getLastname());
        }
    }
}
