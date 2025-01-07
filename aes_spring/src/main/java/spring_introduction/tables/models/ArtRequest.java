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
    private Long workerId;

    @Column(name = "STATUSID")
    private  Long statusId;

    @Column(name = "ROLEID")
    private Long roleId;

    @Column(name = "SERVICEID")
    private Long serviceId;

    @Column(name = "REQUESTDATE")
    private Timestamp requestDate;

    @Column(name = "APPROVALDATE")
    private Timestamp approvalDate;

    @Column(name = "REJECTIONREASON")
    private String rejectionReason;

    @Column(name = "FULLNAME")
    private String fullname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getStatusId() {
        return statusId;
    }

    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
