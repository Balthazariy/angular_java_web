package spring_introduction.tables.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_introduction.tables.models.ArtDepartment;

@Repository
public interface ArtDepartmentRepository extends JpaRepository<ArtDepartment, Long> {
}