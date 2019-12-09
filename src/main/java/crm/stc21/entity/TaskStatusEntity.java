package crm.stc21.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "task_statuses", schema = "public")
public class TaskStatusEntity {

    @Id
    @GeneratedValue(generator = "task_statuses_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "task_statuses_seq", allocationSize = 1, sequenceName = "task_statuses_id_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "name", nullable = true, length = 255)
    private String name;
    
    @Basic
    @Column(name = "display_name", nullable = true, length = 255)
    private String displayName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "taskStatus")
    private Set<TaskEntity> tasks = new HashSet<>();

    public Set<TaskEntity> getTasks() {
        return tasks;
    }

    public void setTasks(Set<TaskEntity> tasks) {
        this.tasks = tasks;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskStatusEntity that = (TaskStatusEntity) o;
        return id.equals(that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(displayName, that.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, displayName);
    }

    @Override
    public String toString() {
        return displayName;
    }
}
