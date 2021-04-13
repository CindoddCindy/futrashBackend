package futrashapi.futrashapiproject.flow_handle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import futrashapi.futrashapiproject.auth.model.User;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

@Entity
@Table(name = "orders")
public class Order extends AuditModel{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String image_url;

   //tambahin kayak di item

    @NotNull
    private String customer_name;

    @Nullable
    private String customer_location;

    @Nullable
    private String customer_phone;

    @Nullable
    private String shipping_type;




    @NotNull
    private String jenis_makanan;

    @NotNull
    private String tidak_dikonsumsi_sejak;

    @NotNull
    private String dijual_karena;

    @NotNull
    private String berat_makanan;

    @NotNull
    private String nama_toko;

    @NotNull
    private String nama_penjual;

    @NotNull
    private String lokasi_makanan;

    @NotNull
    private String harga_makanan;

    @NotNull
    private String saran_penggunaan;

    @NotNull
    private String kandungan_kimia;

    @NotNull
    private String phone_number;

    @NotNull
    private String item_date;

    @NotNull
    private Long id_buyer;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)

    //@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private User user;

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }


    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_location() {
        return customer_location;
    }

    public void setCustomer_location(String customer_location) {
        this.customer_location = customer_location;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public String getShipping_type() {
        return shipping_type;
    }

    public void setShipping_type(String shipping_type) {
        this.shipping_type = shipping_type;
    }

    public String getJenis_makanan() {
        return jenis_makanan;
    }

    public void setJenis_makanan(String jenis_makanan) {
        this.jenis_makanan = jenis_makanan;
    }

    public String getTidak_dikonsumsi_sejak() {
        return tidak_dikonsumsi_sejak;
    }

    public void setTidak_dikonsumsi_sejak(String tidak_dikonsumsi_sejak) {
        this.tidak_dikonsumsi_sejak = tidak_dikonsumsi_sejak;
    }

    public String getDijual_karena() {
        return dijual_karena;
    }

    public void setDijual_karena(String dijual_karena) {
        this.dijual_karena = dijual_karena;
    }

    public String getBerat_makanan() {
        return berat_makanan;
    }

    public void setBerat_makanan(String berat_makanan) {
        this.berat_makanan = berat_makanan;
    }

    public String getNama_toko() {
        return nama_toko;
    }

    public void setNama_toko(String nama_toko) {
        this.nama_toko = nama_toko;
    }

    public String getNama_penjual() {
        return nama_penjual;
    }

    public void setNama_penjual(String nama_penjual) {
        this.nama_penjual = nama_penjual;
    }

    public String getLokasi_makanan() {
        return lokasi_makanan;
    }

    public void setLokasi_makanan(String lokasi_makanan) {
        this.lokasi_makanan = lokasi_makanan;
    }

    public String getHarga_makanan() {
        return harga_makanan;
    }

    public void setHarga_makanan(String harga_makanan) {
        this.harga_makanan = harga_makanan;
    }

    public String getSaran_penggunaan() {
        return saran_penggunaan;
    }

    public void setSaran_penggunaan(String saran_penggunaan) {
        this.saran_penggunaan = saran_penggunaan;
    }

    public String getKandungan_kimia() {
        return kandungan_kimia;
    }

    public void setKandungan_kimia(String kandungan_kimia) {
        this.kandungan_kimia = kandungan_kimia;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getItem_date() {
        return item_date;
    }

    public void setItem_date(String item_date) {
        this.item_date = item_date;
    }

    public Long getId_buyer() {
        return id_buyer;
    }

    public void setId_buyer(Long id_buyer) {
        this.id_buyer = id_buyer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
