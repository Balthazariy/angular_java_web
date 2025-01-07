package spring_introduction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_introduction.tables.interfaces.ArtAccessRepository;
import spring_introduction.tables.interfaces.ArtRequestRepository;
import spring_introduction.tables.models.ArtRequest;
import spring_introduction.tables.models.RequestResponse;
import spring_introduction.tables.models.UpdateRequestStatusResponce;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RequestService {
    @Autowired
    private ArtRequestRepository requestRepository;
    @Autowired
    private AccessService accessService;

    public List<ArtRequest> findRequestsByWorkerId(Long workerId) {
        return requestRepository.findByWorkerId(workerId);
    }

    public List<ArtRequest> getAllRequests() {
        return new ArrayList<>(requestRepository.findAll());
    }

    public ArtRequest updateRequestStatus(Long requestId, UpdateRequestStatusResponce responce) {
        Optional<ArtRequest> optionalRequest = requestRepository.findById(requestId);
        if (optionalRequest.isPresent()) {
            ArtRequest request = optionalRequest.get();
            request.setStatusId(responce.getStatusId());

            if (responce.getStatusId() == 4) {
                accessService.grantAccess(request.getServiceId(), responce.getRoleId(), request.getWorkerId());
                requestRepository.delete(request);
            } else {
                return requestRepository.save(request);
            }
        }
        return null;
    }

    public ArtRequest createRequest(RequestResponse response) {
        ArtRequest request = new ArtRequest();
        request.setWorkerId(response.getWorkerId());
        request.setRoleId(response.getRoleId());
        request.setRequestDate(new Timestamp(System.currentTimeMillis()));
        request.setApprovalDate(null);
        request.setServiceId(response.getServiceId());
        request.setStatusId((long)1);
        request.setRejectionReason("");
        request.setFullname(response.getFullname());
        return requestRepository.save(request);
    }
}