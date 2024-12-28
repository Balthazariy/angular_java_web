package spring_introduction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_introduction.tables.interfaces.*;
import spring_introduction.tables.models.*;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.List;

@Service
public class RequestService {
    @Autowired
    private ArtRoleRepository roleRepository;
    @Autowired
    private ArtServiceRepository serviceRepository;
    @Autowired
    private ArtStatusRepository statusRepository;
    @Autowired
    private ArtRequestRepository requestRepository;
    @Autowired
    private ArtWorkerRepository workerRepository;

    private long countOfRequests = 0;

    public List<BaseResponse> getAllRoleNames() {
        List<BaseResponse> list = new ArrayList<>();
        for (ArtRole artRole : roleRepository.findAll()) {
            BaseResponse response = new BaseResponse(artRole.getName(), artRole.getId());
            list.add(response);
        }
        return list;
    }

    public List<String> getAllServiceNames() {
        List<String> list = new ArrayList<>();
        for (ArtService artService : serviceRepository.findAll()) {
            String name = artService.getName();
            list.add(name);
        }
        return list;
    }

    public List<String> getAllStatusNames() {
        List<String> list = new ArrayList<>();
        for (ArtStatus artStatus : statusRepository.findAll()) {
            String name = artStatus.getName();
            list.add(name);
        }
        return list;
    }

    public List<ArtRequest> getAllRequests() {
        countOfRequests = requestRepository.findAll().size();
        return requestRepository.findAll();
    }

    public ArtRequest createRequest(ArtRequest request) {
        countOfRequests += 1;
        request.setId(countOfRequests);
        return requestRepository.save(request);
    }
}
