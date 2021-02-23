package com.aldiariq.rapidapps.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_lokasirapid")
public class LokasiRapid {
    @Id
    @GeneratedValue
    @Column(name = "id_lokasirapid")
    private int idlokasirapid;
    @Column(name = "nama_lokasirapid")
    private String namalokasirapid;
    @Column(name = "notel_lokasirapid")
    private String notellokasirapid;
    @Column(name = "biaya_lokasirapid")
    private int biayalokasirapid;
    @Column(name = "alamat_lokasirapid")
    private String alamatlokasirapid;
    @Column(name = "longitude_lokasirapid")
    private String longitudelokasirapid;
    @Column(name = "latitude_lokasirapid")
    private String latitudelokasirapid;
    @Column(name = "id_user")
    private int iduser;
    @Column(name = "tgl_dibuat_lokasirapid")
    private String tgldibuatlokasirapid;
    @Column(name = "tgl_diupdate_lokasirapid")
    private String tgldiupdatelokasirapid;
}
