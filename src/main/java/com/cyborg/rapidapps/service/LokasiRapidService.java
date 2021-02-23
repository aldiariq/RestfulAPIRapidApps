package com.cyborg.rapidapps.service;

import com.cyborg.rapidapps.repository.LokasiRapidRepository;
import com.cyborg.rapidapps.entity.LokasiRapid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;

@Service
public class LokasiRapidService {
    @Autowired
    private LokasiRapidRepository lokasiRapidRepository;

    public LokasiRapid tambahLokasirapid(LokasiRapid lokasiRapid) {
        return lokasiRapidRepository.save(lokasiRapid);
    }

    public List<LokasiRapid> lihatLokasirapid(){
        return lokasiRapidRepository.findAll();
    }

    public LokasiRapid lihatLokasirapidbyId(int idlokasirapid){
        return lokasiRapidRepository.findById(idlokasirapid).orElse(null);
    }

    public boolean ubahLokasirapid(LokasiRapid lokasiRapid){
        LokasiRapid lokasiRapidsekarang = lokasiRapidRepository.findById(lokasiRapid.getIdlokasirapid()).orElse(null);
        if (isNull(lokasiRapidsekarang)){
            return false;
        }else {
            lokasiRapidsekarang.setNamalokasirapid(lokasiRapid.getNamalokasirapid());
            lokasiRapidsekarang.setNotellokasirapid(lokasiRapid.getNotellokasirapid());
            lokasiRapidsekarang.setBiayalokasirapid(lokasiRapid.getBiayalokasirapid());
            lokasiRapidsekarang.setAlamatlokasirapid(lokasiRapid.getAlamatlokasirapid());
            lokasiRapidsekarang.setLongitudelokasirapid(lokasiRapid.getLongitudelokasirapid());
            lokasiRapidsekarang.setLatitudelokasirapid(lokasiRapid.getLatitudelokasirapid());
            lokasiRapidsekarang.setIduser(lokasiRapid.getIduser());
            lokasiRapidsekarang.setTgldiupdatelokasirapid(lokasiRapid.getTgldiupdatelokasirapid());
            lokasiRapidRepository.save(lokasiRapidsekarang);
            return true;
        }
    }

    public boolean hapusLokasirapid(int idlokasirapid){
        LokasiRapid lokasiRapidsekarang = lokasiRapidRepository.findById(idlokasirapid).orElse(null);
        if (isNull(lokasiRapidsekarang)){
            return false;
        }else {
            lokasiRapidRepository.deleteById(idlokasirapid);
            return true;
        }
    }
}
