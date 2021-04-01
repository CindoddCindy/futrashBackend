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
    private String image_url;

    @NotNull
    private String terima_tolak;

    @NotNull
    private String catatan_alasan;

    @NotNull
    private String jenis_makanan;

    @NotNull
    private String lokasi_customer;

    @NotNull
    private String nama_customer;

    @NotNull
    private String phone_customer;

    @NotNull
    private String lokasi_mitra;

    @NotNull
    private String nama_mitra;

    @NotNull
    private String phone_mitra;

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

    public String getJenis_makanan() {
        return jenis_makanan;
    }

    public void setJenis_makanan(String jenis_makanan) {
        this.jenis_makanan = jenis_makanan;
    }

    public String getLokasi_customer() {
        return lokasi_customer;
    }

    public void setLokasi_customer(String lokasi_customer) {
        this.lokasi_customer = lokasi_customer;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public void setNama_customer(String nama_customer) {
        this.nama_customer = nama_customer;
    }

    public String getPhone_customer() {
        return phone_customer;
    }

    public void setPhone_customer(String phone_customer) {
        this.phone_customer = phone_customer;
    }

    public String getLokasi_mitra() {
        return lokasi_mitra;
    }

    public void setLokasi_mitra(String lokasi_mitra) {
        this.lokasi_mitra = lokasi_mitra;
    }

    public String getNama_mitra() {
        return nama_mitra;
    }

    public void setNama_mitra(String nama_mitra) {
        this.nama_mitra = nama_mitra;
    }

    public String getPhone_mitra() {
        return phone_mitra;
    }

    public void setPhone_mitra(String phone_mitra) {
        this.phone_mitra = phone_mitra;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
