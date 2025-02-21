package spring_introduction.tables.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_introduction.tables.models.ArtRequest;

import java.util.List;

@Repository
public interface ArtRequestRepository extends JpaRepository<ArtRequest, Long> {
    List<ArtRequest> findByWorkerId(Long workerId);
}