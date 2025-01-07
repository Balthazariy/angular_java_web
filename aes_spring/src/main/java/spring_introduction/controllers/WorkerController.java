package spring_introduction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_introduction.services.WorkerService;
import spring_introduction.tables.models.ArtWorker;

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping("/{workerId}")
    public ResponseEntity<ArtWorker> getWorkerById(@PathVariable("workerId") Long workerId) {
        ArtWorker worker = workerService.findWorkerById(workerId);
        if (worker != null) {
            return ResponseEntity.ok(worker);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
