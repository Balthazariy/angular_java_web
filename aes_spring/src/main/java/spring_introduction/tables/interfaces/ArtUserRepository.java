package spring_introduction.tables.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_introduction.tables.models.ArtUser;

@Repository
public interface ArtUserRepository extends JpaRepository<ArtUser, String> {
    ArtUser findByUsername(String username);
}