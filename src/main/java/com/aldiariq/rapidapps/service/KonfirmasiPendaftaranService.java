package com.aldiariq.rapidapps.service;

import com.aldiariq.rapidapps.entity.KonfirmasiPendaftaran;
import com.aldiariq.rapidapps.repository.KonfirmasiPendaftaranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KonfirmasiPendaftaranService {
    @Autowired
    private KonfirmasiPendaftaranRepository konfirmasiPendaftaranRepository;

    public KonfirmasiPendaftaran tambahkonfirmasiPendaftaran(KonfirmasiPendaftaran konfirmasiPendaftaran) {
        return konfirmasiPendaftaranRepository.save(konfirmasiPendaftaran);
    }

    public boolean konfirmasiPendaftaran(int idkonfirmasipendaftaran) {
        if (konfirmasiPendaftaranRepository.existsById(idkonfirmasipendaftaran)) {
            konfirmasiPendaftaranRepository.deleteById(idkonfirmasipendaftaran);
            return true;
        } else {
            return false;
        }
    }

    public List<KonfirmasiPendaftaran> lihatkonfirmasiPendaftaran(){
        return konfirmasiPendaftaranRepository.findAll();
    }
}
