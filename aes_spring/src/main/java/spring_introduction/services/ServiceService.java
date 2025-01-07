package spring_introduction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_introduction.tables.interfaces.ArtServiceRepository;
import spring_introduction.tables.models.ArtAccess;
import spring_introduction.tables.models.ArtService;
import spring_introduction.tables.models.BaseResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceService {
    @Autowired
    private ArtServiceRepository serviceRepository;

    public List<BaseResponse> getAllServiceNames() {
        List<BaseResponse> list = new ArrayList<>();
        for (ArtService artService : serviceRepository.findAll()) {
            BaseResponse response = new BaseResponse(artService.getName(), artService.getId());
            list.add(response);
        }
        return list;
    }
}