package spring_introduction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_introduction.tables.models.ArtDepartment;
import spring_introduction.tables.interfaces.ArtDepartmentRepository;
import spring_introduction.tables.models.ArtRole;
import spring_introduction.tables.interfaces.ArtRoleRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {
    @Autowired
    private ArtRoleRepository roleRepository;
    @Autowired
    private ArtDepartmentRepository departmentRepository;

    public List<String> getAllRoleNames() {
        List<String> list = new ArrayList<>();
        for (ArtRole artRole : roleRepository.findAll()) {
            String name = artRole.getName();
            list.add(name);
        }
        System.out.println(list);
        return list;
    }

    public List<String> getAllDepartmentNames() {
        List<String> list = new ArrayList<>();
        for (ArtDepartment artDepartment : departmentRepository.findAll()) {
            String name = artDepartment.getName();
            list.add(name);
        }
        System.out.println(list);
        return list;
    }
}
