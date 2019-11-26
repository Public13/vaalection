package ru.protei.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.protei.backend.dao.DepartmentsDao;
import ru.protei.model.Department;

import java.util.List;

@Service
public class DepartmentsService {

    @Autowired
    private DepartmentsDao departmentsDao;

    public boolean save(Department department) {
        if (department == null)
            return false;
        departmentsDao.update(department);
        return true;
    }

    public List<Department> getDepartments() {
        return departmentsDao.getAll();
    }
}
