package spring_introduction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_introduction.services.RequestService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestController {

    @Autowired
    private RequestService service;

    @GetMapping("/roles")
    public List<String> getAllRoles() {
        return service.getAllRoleNames();
    }

    @GetMapping("/department")
    public List<String> getAllDepartments() {
        return service.getAllDepartmentNames();
    }
}
