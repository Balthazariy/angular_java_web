package spring_introduction.tables.models;

public class LoginResponse {
    private String message;
    private Long workerId;

    public LoginResponse(String message, Long workerId) {
        this.message = message;
        this.workerId = workerId;
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
}
