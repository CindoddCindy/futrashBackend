package futrashapi.futrashapiproject.flow_handle.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "charts")
public class Chart extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String image_url;

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




}
