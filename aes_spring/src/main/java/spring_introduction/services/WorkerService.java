package spring_introduction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_introduction.tables.interfaces.ArtWorkerRepository;
import spring_introduction.tables.models.ArtAccess;
import spring_introduction.tables.models.ArtWorker;

@Service
public class WorkerService {

    @Autowired
    private ArtWorkerRepository workerRepository;

    public ArtWorker findWorkerById(Long workerId) {
        return workerRepository.findById(workerId).orElse(null);
    }
}
