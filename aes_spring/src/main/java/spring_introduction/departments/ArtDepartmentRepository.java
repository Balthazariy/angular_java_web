package spring_introduction.departments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_introduction.roles.ArtRole;

@Repository
public interface ArtDepartmentRepository extends JpaRepository<ArtDepartment, Long> {
}