package spring_introduction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_introduction.tables.models.ArtUser;
import spring_introduction.tables.interfaces.ArtUserRepository;

@Service
public class LoginService {
    @Autowired
    private ArtUserRepository userRepository;

    public ArtUser findUser(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean authenticate(String username, String password) {
        ArtUser user = findUser(username);

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }
}