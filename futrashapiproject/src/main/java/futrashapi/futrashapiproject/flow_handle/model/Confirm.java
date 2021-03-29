package futrashapi.futrashapiproject.flow_handle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import futrashapi.futrashapiproject.auth.model.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "confirm")

public class Confirm extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String terima_tolak;

    @NotNull
    private String catatan_alasan;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;


    public Confirm() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTerima_tolak() {
        return terima_tolak;
    }

    public void setTerima_tolak(String terima_tolak) {
        this.terima_tolak = terima_tolak;
    }

    public String getCatatan_alasan() {
        return catatan_alasan;
    }

    public void setCatatan_alasan(String catatan_alasan) {
        this.catatan_alasan = catatan_alasan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
