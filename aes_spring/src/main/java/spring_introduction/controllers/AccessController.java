package spring_introduction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring_introduction.services.AccessService;
import spring_introduction.tables.models.ArtAccess;

import java.util.List;

@RestController
@RequestMapping("/api/access")
public class AccessController {
    @Autowired
    private AccessService accessService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<ArtAccess>> getAccessesById(@PathVariable("userId") Long userId) {
        List<ArtAccess> accesses = accessService.findAccessesByUserId(userId);
        if (!accesses.isEmpty()) {
            return ResponseEntity.ok(accesses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}