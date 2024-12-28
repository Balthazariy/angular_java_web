package spring_introduction.tables.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_introduction.tables.models.ArtRequest;

@Repository
public interface ArtRequestRepository extends JpaRepository<ArtRequest, Long> {
}