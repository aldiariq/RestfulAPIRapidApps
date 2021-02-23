package com.cyborg.rapidapps.entity.autentikasi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UbahPassword {
    private int iduser;
    private String passwordlama;
    private String passwordbaru;
    private String tgldiupdateuser;
}
