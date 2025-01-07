package spring_introduction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_introduction.services.RequestService;
import spring_introduction.tables.models.ArtRequest;
import spring_introduction.tables.models.BaseResponse;
import spring_introduction.tables.models.RequestResponse;
import spring_introduction.tables.models.UpdateRequestStatusResponce;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RequestController {
    @Autowired
    private RequestService requestService;

    @GetMapping("/request/{workerId}")
    public ResponseEntity<List<ArtRequest>> getRequestsById(@PathVariable("workerId") Long workerId) {
        List<ArtRequest> requests = requestService.findRequestsByWorkerId(workerId);
        if (!requests.isEmpty()) {
            return ResponseEntity.ok(requests);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/requests")
    public List<ArtRequest> getAllRequests() {
        return requestService.getAllRequests();
    }

    @PatchMapping("/updateRequest/{requestId}")
    public ArtRequest updateRequestStatus(@PathVariable("requestId") Long requestId, @RequestBody UpdateRequestStatusResponce responce) {
        return requestService.updateRequestStatus(requestId, responce);
    }

    @PostMapping("/create")
    public ArtRequest createRequest(@RequestBody RequestResponse request) {
        return requestService.createRequest(request);
    }
}