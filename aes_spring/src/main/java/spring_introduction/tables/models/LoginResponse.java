package spring_introduction.tables.models;

public class LoginResponse {
    private String message;
    private Long workerId;
    private Long userId;

    public LoginResponse(String message, Long workerId, Long userId) {
        this.message = message;
        this.workerId = workerId;
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
