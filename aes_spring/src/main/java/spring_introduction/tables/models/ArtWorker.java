package spring_introduction.tables.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

@Entity
@Table(name = "ART_WORKER")
public class ArtWorker {
    @Id
    @Column(name = "WORKERID")
    private Long id;

    @Column(name = "HIREDATE")
    private Timestamp hiredate;

    @Column(name = "FULLNAME")
    private String fullname;

    @Column(name = "DEPARTMENTID")
    private long departmentId;

    @Column(name = "POSITIONID")
    private long positionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getHiredate() {
        return hiredate;
    }

    public void setHiredate(Timestamp hiredate) {
        this.hiredate = hiredate;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public long getPositionId() {
        return positionId;
    }

    public void setPositionId(long positionId) {
        this.positionId = positionId;
    }
}
