package spring_introduction.roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtRoleRepository extends JpaRepository<ArtRole, Long> {
}
