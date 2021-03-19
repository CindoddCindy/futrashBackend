package futrashapi.futrashapiproject.flow_handle.exception.message;

import com.sun.istack.Nullable;
import futrashapi.futrashapiproject.auth.model.User;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ResponseItem {

    private String name;
    private String url;
    private String type;
    private long size;

    //tambah



    private String jenis_makanan;

    private String tidak_dikonsumsi_sejak;

    private String dijual_karena;

    private String berat_makanan;

    private String nama_toko;

    private String nama_penjual;

    private String lokasi_makanan;

    private String harga_makanan;

    private String saran_penggunaan;

    private String kandungan_kimia;

    private List<User> userList;


    public ResponseItem(String name, String url, String type, long size) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
    }


    public ResponseItem(String name, String url, String type, long size, String jenis_makanan, String tidak_dikonsumsi_sejak, String dijual_karena, String berat_makanan, String nama_toko, String nama_penjual, String lokasi_makanan, String harga_makanan, String saran_penggunaan, String kandungan_kimia, List<User> userList) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
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

    public ResponseItem(String name, String url, String type, long size, String jenis_makanan, String tidak_dikonsumsi_sejak, String dijual_karena, String berat_makanan, String nama_toko, String nama_penjual, String lokasi_makanan, String harga_makanan, String saran_penggunaan, String kandungan_kimia) {
        this.name = name;
        this.url = url;
        this.type = type;
        this.size = size;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
