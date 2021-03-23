package futrashapi.futrashapiproject.flow_handle.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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



    @Nullable
    private String jenis_makanan;

    @Nullable
    private String tidak_dikonsumsi_sejak;

    @Nullable
    private String dijual_karena;

    @Nullable
    private String berat_makanan;

    @Nullable
    private String nama_toko;

    @Nullable
    private String nama_penjual;

    @Nullable
    private String lokasi_makanan;

    @Nullable
    private String harga_makanan;

    @Nullable
    private String saran_penggunaan;

    @Nullable
    private String kandungan_kimia;

    public Item() {
    }






    public Item(String name, String type, byte[] data, String jenis_makanan, String tidak_dikonsumsi_sejak, String dijual_karena, String berat_makanan, String nama_toko, String nama_penjual, String lokasi_makanan, String harga_makanan, String saran_penggunaan, String kandungan_kimia) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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


}
