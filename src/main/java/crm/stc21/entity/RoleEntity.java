package crm.stc21.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles", schema = "public")
public class RoleEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "roles_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "roles_seq", allocationSize = 1, sequenceName = "roles_id_seq")
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "display_name", nullable = false, length = 255)
    private String displayName;

    @Column(name = "description", nullable = true, length = 255)
    private String description;

    @Column(name = "created_at", nullable = true)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<PermissionEntity> permissions;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "role")
    private Set<UserEntity> users = new HashSet<>();

    public Set<PermissionEntity> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionEntity> permissions) {
        this.permissions = permissions;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

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

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
