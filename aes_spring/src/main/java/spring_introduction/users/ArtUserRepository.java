package spring_introduction.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtUserRepository extends JpaRepository<ArtUser, String> {
    ArtUser findByUsername(String username);
}