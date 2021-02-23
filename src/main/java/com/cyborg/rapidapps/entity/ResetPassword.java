package com.cyborg.rapidapps.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_resetpassword")
public class ResetPassword {
    @Id
    @GeneratedValue
    @Column(name = "id_resetpassword")
    private int idresetpassword;
    @Column(name = "email_resetpassword")
    private String emailresetpassword;
    @Column(name = "kodeunik_resetpassword")
    private String kodeunikresetpassword;
    @Column(name = "id_user")
    private int iduser;
}
