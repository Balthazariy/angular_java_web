package spring_introduction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_introduction.services.ServiceService;
import spring_introduction.tables.models.BaseResponse;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping("/service")
    public List<BaseResponse> getAllServices() {
        return serviceService.getAllServiceNames();
    }
}
