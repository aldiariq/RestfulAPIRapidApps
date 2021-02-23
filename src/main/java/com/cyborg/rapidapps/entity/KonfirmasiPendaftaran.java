package com.cyborg.rapidapps.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_konfirmasipendaftaran")
public class KonfirmasiPendaftaran {
    @Id
    @GeneratedValue
    @Column(name = "id_konfirmasipendaftaran")
    private int idkonfirmasipendaftaran;
    @Column(name = "email_konfirmasipendaftaran")
    private String emailkonfirmasipendaftaran;
    @Column(name = "kodeunik_konfirmasipendaftaran")
    private String kodeunikkonfirmasipendaftaran;
    @Column(name = "id_user")
    private int iduser;
}
