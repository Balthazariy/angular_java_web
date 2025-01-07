package spring_introduction.tables.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_introduction.tables.models.ArtAccess;

import java.util.List;

@Repository
public interface ArtAccessRepository extends JpaRepository<ArtAccess, Long> {
    List<ArtAccess> findAllByUserId(Long userId);
}