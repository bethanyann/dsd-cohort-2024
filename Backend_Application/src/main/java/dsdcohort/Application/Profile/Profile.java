package dsdcohort.Application.Profile;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Profile {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private int mainSkillId;
    @CreationTimestamp
    private LocalDateTime crDateTime;
    @UpdateTimestamp
    private LocalDateTime upDateTime;

    public Profile() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMainSkillId() {
        return mainSkillId;
    }

    public void setMainSkillId(int mainSkillId) {
        this.mainSkillId = mainSkillId;
    }

    public LocalDateTime getCrDateTime() {
        return crDateTime;
    }

    public void setCrDateTime(LocalDateTime crDateTime) {
        this.crDateTime = crDateTime;
    }

    public LocalDateTime getUpDateTime() {
        return upDateTime;
    }

    public void setUpDateTime(LocalDateTime upDateTime) {
        this.upDateTime = upDateTime;
    }
}
