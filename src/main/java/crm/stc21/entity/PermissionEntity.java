package crm.stc21.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "permissions", schema = "public")
public class PermissionEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "permissions_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "permissions_seq", allocationSize = 1, sequenceName = "permissions_id_seq")
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "display_name", nullable = false, length = 255)
    private String displayName;

    @Column(name = "created_at", nullable = true)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;

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
}
