package ru.protei.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.protei.backend.dao.DevelopersDao;
import ru.protei.model.Developer;

import java.util.List;

@Service
public class DevelopersService {

    @Autowired
    private DevelopersDao developersDao;

    public boolean save(Developer developer) {
        if (developer == null)
            return false;
        developersDao.update(developer);
        return true;
    }

    public List<Developer> getDevelopers() {
        return developersDao.getAll();
    }
}
