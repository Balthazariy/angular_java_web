package spring_introduction.tables.models;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "ART_REQUEST")
public class ArtRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REQUESTID")
    private Long id;

    @Column(name = "WORKERID")
    private Long workerID;

    @Column(name = "STATUSID")
    private  Long statusID;

    @Column(name = "ROLEID")
    private Long roleID;

    @Column(name = "REQUESTDATE")
    private Timestamp requestDate;

    @Column(name = "APPROVALDATE")
    private Timestamp approvalDate;

    @Column(name = "REJECTIONREASON")
    private String rejectionReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkerID() {
        return workerID;
    }

    public void setWorkerID(Long workerID) {
        this.workerID = workerID;
    }

    public Long getStatusID() {
        return statusID;
    }

    public void setStatusID(Long statusID) {
        this.statusID = statusID;
    }

    public Long getRoleID() {
        return roleID;
    }

    public void setRoleID(Long roleID) {
        this.roleID = roleID;
    }

    public Timestamp getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Timestamp requestDate) {
        this.requestDate = requestDate;
    }

    public Timestamp getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Timestamp approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
}
