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



}
