package futrashapi.futrashapiproject.flow_handle.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.sun.istack.Nullable;
import futrashapi.futrashapiproject.auth.model.User;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "items")
public class Item extends  AuditModel{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String name;

    private String type;

    @Lob
    private byte[] data;

    //variabel lainnya

    @NotNull
    private String jenis_makanan;

    @NotNull
    private String tidak_dikonsumsi_sejak;

    @NotNull
    private String dijual_karena;

    @NotNull
    private String berat_makanan;

    @Nullable
    private String nama_toko;

    @NotNull
    private String nama_penjual;

    @NotNull
    private String lokasi_makanan;

    @NotNull
    private String harga_makanan;

    @Nullable
    private String saran_penggunaan;

    @NotNull
    private String kandungan_kimia;

    @OneToMany(targetEntity = User.class, cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<User> userList;



    @OneToMany(mappedBy = "items", cascade = CascadeType.ALL)
    private Set<Chart> charts = new HashSet<>();



    @OneToMany(mappedBy = "items", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();


    public Item() {
    }

    public Item(String name, String type, byte[] data) {
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public Item(String name, String type, byte[] data, @NotNull String jenis_makanan, @NotNull String tidak_dikonsumsi_sejak, @NotNull String dijual_karena, @NotNull String berat_makanan, String nama_toko, @NotNull String nama_penjual, @NotNull String lokasi_makanan, @NotNull String harga_makanan, String saran_penggunaan, @NotNull String kandungan_kimia, List<User> userList) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.jenis_makanan = jenis_makanan;
        this.tidak_dikonsumsi_sejak = tidak_dikonsumsi_sejak;
        this.dijual_karena = dijual_karena;
        this.berat_makanan = berat_makanan;
        this.nama_toko = nama_toko;
        this.nama_penjual = nama_penjual;
        this.lokasi_makanan = lokasi_makanan;
        this.harga_makanan = harga_makanan;
        this.saran_penggunaan = saran_penggunaan;
        this.kandungan_kimia = kandungan_kimia;
       this.userList = userList;
    }



    public Item(String name, String type, byte[] data, @NotNull String jenis_makanan, @NotNull String tidak_dikonsumsi_sejak, @NotNull String dijual_karena, @NotNull String berat_makanan, String nama_toko, @NotNull String nama_penjual, @NotNull String lokasi_makanan, @NotNull String harga_makanan, String saran_penggunaan, @NotNull String kandungan_kimia) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.jenis_makanan = jenis_makanan;
        this.tidak_dikonsumsi_sejak = tidak_dikonsumsi_sejak;
        this.dijual_karena = dijual_karena;
        this.berat_makanan = berat_makanan;
        this.nama_toko = nama_toko;
        this.nama_penjual = nama_penjual;
        this.lokasi_makanan = lokasi_makanan;
        this.harga_makanan = harga_makanan;
        this.saran_penggunaan = saran_penggunaan;
        this.kandungan_kimia = kandungan_kimia;
    }

    public void setId(String id) {
        this.id = id;
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

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }



    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }


    public Set<Chart> getCharts() {
        return charts;
    }

    public void setCharts(Set<Chart> charts) {
        this.charts = charts;

        for(Chart c : charts) {
            c.setItem(this);
        }
    }



    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;

        for(Order o : orders) {
            o.setItem(this);
        }
    }
}