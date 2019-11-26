package ru.protei.backend.dao;

import org.springframework.stereotype.Service;
import ru.protei.model.Department;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentsDao {

    private List<Department> departments;
    {
        departments = new ArrayList<>();
        departments.add(new Department(1, "Отдел номер 7", 100));
        departments.add(new Department(2, "Отдел номер 3", 50));
        departments.add(new Department(3, "Отдел номер 1", 75));
    }

    public List<Department> getAll() {
        return departments;
    }

    public Department findById(int id) {
        return departments.stream().filter(e -> e.getId() == id).findFirst().orElse(null);
    }

    public void update(Department department) {
        Department findDepartment = findById(department.getId());
        if (findDepartment != null) {
            findDepartment.setName(department.getName());
            findDepartment.setRating(department.getRating());
        }
    }
}
