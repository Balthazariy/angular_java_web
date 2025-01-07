package spring_introduction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_introduction.tables.interfaces.ArtAccessRepository;
import spring_introduction.tables.interfaces.ArtUserRepository;
import spring_introduction.tables.models.ArtAccess;
import spring_introduction.tables.models.ArtRequest;
import spring_introduction.tables.models.ArtUser;
import spring_introduction.tables.models.ArtWorker;

import java.util.List;

@Service
public class AccessService {
    @Autowired
    private ArtAccessRepository accessRepository;
    @Autowired
    private ArtUserRepository userRepository;

    public List<ArtAccess> findAccessesByUserId(Long userId) {
        return accessRepository.findAllByUserId(userId);
    }

    public void grantAccess(Long serviceId, Long roleId, Long workerId) {
        List<ArtUser> users = userRepository.findAll();
        ArtUser userToGrantAccess = new ArtUser();
        for (int i = 0; i < users.size(); i++) {
            if (workerId.equals(users.get(i).getWorkerid())) {
                userToGrantAccess = users.get(i);
            }
        }

        List<ArtAccess> accesses = accessRepository.findAllByUserId(workerId);
        ArtAccess access = new ArtAccess();
        access.setUserId(userToGrantAccess.getId());
        access.setServiceId(serviceId);
        access.setRoleId(roleId);
        accessRepository.save(access);
    }
}