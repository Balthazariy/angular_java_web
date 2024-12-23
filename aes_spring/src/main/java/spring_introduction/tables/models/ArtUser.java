package spring_introduction.tables.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ART_USER")
public class ArtUser {
    @Id
    @Column(name = "USERID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOGIN")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "WORKERID")
    private Long workerid;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}