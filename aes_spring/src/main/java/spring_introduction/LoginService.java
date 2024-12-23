package spring_introduction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_introduction.users.ArtUser;
import spring_introduction.users.ArtUserRepository;

@Service
public class LoginService {
    @Autowired
    private ArtUserRepository userRepository;

    public boolean authenticate(String username, String password) {
        ArtUser user = userRepository.findByUsername(username);

        if (user == null) {
            return false;
        }

        return user.getPassword().equals(password);
    }
}
