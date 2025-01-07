package spring_introduction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_introduction.tables.interfaces.ArtRoleRepository;
import spring_introduction.tables.models.ArtRole;
import spring_introduction.tables.models.BaseResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private ArtRoleRepository roleRepository;

    public List<BaseResponse> getAllRoleNames() {
        List<BaseResponse> list = new ArrayList<>();
        for (ArtRole artRole : roleRepository.findAll()) {
            BaseResponse response = new BaseResponse(artRole.getName(), artRole.getId());
            list.add(response);
        }
        return list;
    }
}
