package com.aldiariq.rapidapps.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id_user")
    private int iduser;
    @Column(name = "email_user")
    private String emailuser;
    @Column(name = "nama_user")
    private String namauser;
    @Column(name = "password_user")
    @JsonIgnore
    private String passworduser;
    @Column(name = "status_user")
    private String statususer;
    @Column(name = "akses_user")
    private String aksesuser;
    @Column(name = "tgl_dibuat_user")
    private String tgldibuatuser;
    @Column(name = "tgl_diupdate_user")
    private String tgldiupdateuser;
}
