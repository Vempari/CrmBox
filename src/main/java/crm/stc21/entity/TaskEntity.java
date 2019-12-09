package crm.stc21.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tasks", schema = "public")
public class TaskEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "tasks_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "tasks_seq", allocationSize = 1, sequenceName = "tasks_id_seq")
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "is_accepted", nullable = true)
    private Boolean isAccepted;

    @Column(name = "accept_time", nullable = true)
    private Timestamp acceptTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_created_id")
    private UserEntity userCreated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_updated_id")
    private UserEntity userUpdated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_deleted_id")
    private UserEntity userDeleted;

    @Column(name = "created_at", nullable = true)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;

    @Column(name = "deleted_at", nullable = true)
    private Timestamp deletedAt;

    @Column(name = "is_deleted", nullable = true)
    private Boolean isDeleted;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_user_id")
    private UserEntity user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_status_id")
    private TaskStatusEntity taskStatus;

    public UserEntity getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(UserEntity userCreated) {
        this.userCreated = userCreated;
    }

    public TaskStatusEntity getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatusEntity taskStatusEntity) {
        this.taskStatus = taskStatusEntity;
    }

    public TaskEntity() {
    }

    public TaskEntity(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }

    public Timestamp getAcceptTime() {
        return acceptTime;
    }

    public void setAcceptTime(Timestamp acceptTime) {
        this.acceptTime = acceptTime;
    }

    public UserEntity getUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(UserEntity userUpdated) {
        this.userUpdated = userUpdated;
    }

    public UserEntity getUserDeleted() {
        return userDeleted;
    }

    public void setUserDeleted(UserEntity userDeleted) {
        this.userDeleted = userDeleted;
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

    public Timestamp getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Timestamp deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskEntity that = (TaskEntity) o;
        return id.equals(that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(isAccepted, that.isAccepted) &&
                Objects.equals(acceptTime, that.acceptTime) &&
                Objects.equals(userCreated, that.userCreated)&&
                Objects.equals(userUpdated, that.userUpdated)&&
                Objects.equals(userDeleted, that.userDeleted)&&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt) &&
                Objects.equals(deletedAt, that.deletedAt) &&
                Objects.equals(isDeleted, that.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, isAccepted, acceptTime,
                userCreated, userUpdated, userDeleted, createdAt, updatedAt, deletedAt, isDeleted);
    }

    @Override
    public String toString() {
        return  "id=" + id + "\n" +
                "Название=" + title + "\n" +
                "Описание=" + description + "\n" +
                "Статус задачи=" + taskStatus + "\n" +
                "Исполнитель=" + user.getFirstName() + " " + user.getLastName() + " " + user.getFatherName() + "\n" +
                "=====================================";
    }
}
