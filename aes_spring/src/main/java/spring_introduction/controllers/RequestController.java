package spring_introduction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring_introduction.services.RequestService;
import spring_introduction.tables.models.ArtRequest;
import spring_introduction.tables.models.ArtWorker;
import spring_introduction.tables.models.BaseResponse;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestController {

    @Autowired
    private RequestService service;

    @GetMapping("/roles")
    public List<BaseResponse> getAllRoles() {
        return service.getAllRoleNames();
    }

    @GetMapping("/service")
    public List<String> getAllServices() {
        return service.getAllServiceNames();
    }

    @GetMapping("/status")
    public List<String> getAllStatuses() {
        return service.getAllStatusNames();
    }

    @GetMapping("/request")
    public List<ArtRequest> getAllRequests() {
        return service.getAllRequests();
    }

    @PostMapping("/create")
    public ArtRequest createRequest(@RequestBody ArtRequest request) {
        System.out.println(request);
        return service.createRequest(request);
    }
}
