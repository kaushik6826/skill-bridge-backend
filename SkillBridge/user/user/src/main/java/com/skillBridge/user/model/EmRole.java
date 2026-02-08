package com.skillBridge.user.model;
 
import jakarta.persistence.*;

@Entity
@Table(name = "role", schema = "users", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class EmRole 
{

  @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "role")
    private String role;

    @Column(name = "parent_id")
    private int parentId;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "state_cd")
    private String stateCd;

    // Constructors, getters, and setters
    // Constructor
    public EmRole() {

    }

    public EmRole(String role, Integer parentId, boolean isActive, String stateCd) {
        super();
        this.role = role;
        this.parentId = parentId;
        this.isActive = isActive;
        this.stateCd = stateCd;
    }

    // Getters and Setters

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public String getStateCd() {
        return stateCd;
    }

    public void setStateCd(String stateCd) {
        this.stateCd = stateCd;
    }

    public Integer getId() {
        return id;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "EmRole [id=" + id + ", role=" + role + ", parentId=" + parentId + ", isActive=" + isActive
                + ", stateCd=" + stateCd + "]";
    }

}
