package com.cyborg.rapidapps.response;

import com.cyborg.rapidapps.entity.LokasiRapid;
import com.cyborg.rapidapps.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response implements Serializable {
    private boolean berhasil;
    private String pesan;
    private String token;
    private User user;
    private LokasiRapid lokasirapid;
    private List<LokasiRapid> lokasirapidlist;

    public boolean isBerhasil() {
        return berhasil;
    }

    public void setBerhasil(boolean berhasil) {
        this.berhasil = berhasil;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LokasiRapid getLokasirapid() {
        return lokasirapid;
    }

    public void setLokasirapid(LokasiRapid lokasirapid) {
        this.lokasirapid = lokasirapid;
    }

    public List<LokasiRapid> getLokasirapidlist() {
        return lokasirapidlist;
    }

    public void setLokasirapidlist(List<LokasiRapid> lokasirapidlist) {
        this.lokasirapidlist = lokasirapidlist;
    }
}
