package spring_introduction.tables.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ART_ROLE")
public class ArtRole {
    @Id
    @Column(name = "ROLEID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
