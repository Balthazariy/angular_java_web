package spring_introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_introduction.departments.ArtDepartment;
import spring_introduction.departments.ArtDepartmentRepository;
import spring_introduction.roles.ArtRole;
import spring_introduction.roles.ArtRoleRepository;
import spring_introduction.users.ArtUser;
import spring_introduction.users.ArtUserRepository;

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
