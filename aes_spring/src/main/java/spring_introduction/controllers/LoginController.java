package spring_introduction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_introduction.tables.models.ArtUser;
import spring_introduction.services.LoginService;
import spring_introduction.tables.models.LoginResponse;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody ArtUser loginRequest) {
        boolean isAuthenticated = loginService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
            Long workerId = loginService.findUser(loginRequest.getUsername()).getWorkerid();
            LoginResponse response = new LoginResponse("Login successful!", workerId);

            return ResponseEntity.ok(response);
        } else {
            LoginResponse response = new LoginResponse("Invalid username or password", (long)-1);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
