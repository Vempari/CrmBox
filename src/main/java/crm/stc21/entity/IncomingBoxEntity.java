package crm.stc21.entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(name = "incomingbox", schema = "public")
public class IncomingBoxEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(generator = "incomingbox_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "incomingbox_seq", allocationSize = 1, sequenceName = "incomingbox_id_seq")
    private Long id;

    @Column(name = "year", nullable = false)
    private Long year;

    @Column(name = "numb", nullable = false, length = 255)
    private String number;

    @Column(name = "out_numb", nullable = false, length = 255)
    private String outNumb;

    @Column(name = "doc_date", nullable = false)
    private LocalDate docDate;

    @Column(name = "out_date", nullable = false)
    private LocalDate outDate;

    @Column(name = "title", nullable = false, length = 400)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_updated_id")
    private UserEntity userUpdated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_created_id")
    private UserEntity userCreated;

    @Column(name = "created_at", nullable = true)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = true)
    private Timestamp updatedAt;

    @Column(name = "deleted_at", nullable = true)
    private Timestamp deletedAt;

    @Column(name = "is_deleted", nullable = true)
    private Boolean isDeleted;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOutNumb() {
        return outNumb;
    }

    public void setOutNumb(String outNumb) {
        this.outNumb = outNumb;
    }

    public LocalDate getDocDate() {
        return docDate;
    }

    public void setDocDate(LocalDate docDate) {
        this.docDate = docDate;
    }

    public LocalDate getOutDate() {
        return outDate;
    }

    public void setOutDate(LocalDate outDate) {
        this.outDate = outDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public UserEntity getUserUpdated() {
        return userUpdated;
    }

    public void setUserUpdated(UserEntity userUpdated) {
        this.userUpdated = userUpdated;
    }

    public UserEntity getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(UserEntity userCreated) {
        this.userCreated = userCreated;
    }

    @Override
    public String toString() {
        return  "id=" + id + "\n" +
                "Год=" + year + "\n" +
                "Номер входящего документа=" + number + "\n" +
                "Номер исходящего документа=" + outNumb + '\n' +
                "Дата получения=" + docDate + "\n" +
                "Дата отправки=" + outDate + "\n" +
                "Название=" + title + "\n" +
                "Содержание=" + content + "\n" +
                "=================================";
    }
}
