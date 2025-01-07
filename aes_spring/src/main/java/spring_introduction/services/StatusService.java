package spring_introduction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_introduction.tables.interfaces.ArtStatusRepository;
import spring_introduction.tables.models.ArtStatus;
import spring_introduction.tables.models.BaseResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatusService {
    @Autowired
    private ArtStatusRepository statusRepository;

    public List<BaseResponse> getAllStatusNames() {
        List<BaseResponse> list = new ArrayList<>();
        for (ArtStatus artStatus : statusRepository.findAll()) {
            BaseResponse response = new BaseResponse(artStatus.getName(), artStatus.getId());
            list.add(response);
        }
        return list;
    }
}
