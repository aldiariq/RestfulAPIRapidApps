package com.aldiariq.rapidapps.controller;

import com.aldiariq.rapidapps.response.Response;
import com.aldiariq.rapidapps.service.LokasiRapidService;
import com.aldiariq.rapidapps.entity.LokasiRapid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Objects.isNull;

@RestController
public class LokasiRapidController {

    @Autowired
    private LokasiRapidService lokasiRapidService;

    @PostMapping("/api/tambahlokasirapid")
    public ResponseEntity<Object> tambahLokasirapid(@ModelAttribute LokasiRapid lokasiRapid) {
        Response responseTambahlokasirapid = new Response();
        try {
            LokasiRapid lokasirapidSimpan = lokasiRapidService.tambahLokasirapid(lokasiRapid);
            if (isNull(lokasirapidSimpan)) {
                responseTambahlokasirapid.setBerhasil(false);
                responseTambahlokasirapid.setPesan("Gagal Menyimpan Lokasi Rapid");
            } else {
                responseTambahlokasirapid.setBerhasil(true);
                responseTambahlokasirapid.setPesan("Berhasil Menyimpan Lokasi Rapid");
            }
        } catch (Exception e) {
            responseTambahlokasirapid.setBerhasil(false);
            responseTambahlokasirapid.setPesan("Gagal Menyimpan Lokasi Rapid");
        }

        return ResponseEntity.ok(
                responseTambahlokasirapid
        );
    }

    @GetMapping("/api/lihatlokasirapid")
    public ResponseEntity<Object> lihatLokasirapid() {
        Response responseLihatlokasirapid = new Response();
        try {
            List<LokasiRapid> lokasiRapidList = lokasiRapidService.lihatLokasirapid();
            responseLihatlokasirapid.setBerhasil(true);
            responseLihatlokasirapid.setPesan("Berhasil Mendapatkan Lokasi Rapid");
            responseLihatlokasirapid.setLokasirapidlist(lokasiRapidList);
        } catch (Exception e) {
            responseLihatlokasirapid.setBerhasil(false);
            responseLihatlokasirapid.setPesan("Gagal Mendapatkan Lokasi Rapid");
        }

        return ResponseEntity.ok(
                responseLihatlokasirapid
        );
    }

    @GetMapping("/api/lihatlokasirapid/{id_lokasirapid}")
    public ResponseEntity<Object> lihatLokasirapidbyid(@PathVariable int id_lokasirapid) {
        Response responseLihatlokasirapidbyid = new Response();
        try {
            LokasiRapid lokasiRapid = lokasiRapidService.lihatLokasirapidbyId(id_lokasirapid);
            responseLihatlokasirapidbyid.setBerhasil(true);
            responseLihatlokasirapidbyid.setPesan("Berhasil Mendapatkan Lokasi Rapid");
            responseLihatlokasirapidbyid.setLokasirapid(lokasiRapid);
        } catch (Exception e) {
            responseLihatlokasirapidbyid.setBerhasil(false);
            responseLihatlokasirapidbyid.setPesan("Gagal Mendapatkan Lokasi Rapid");
        }

        return ResponseEntity.ok(
                responseLihatlokasirapidbyid
        );
    }

    @PutMapping("/api/ubahlokasirapid")
    public ResponseEntity<Object> ubahLokasirapid(@ModelAttribute LokasiRapid lokasiRapid) {
        Response responseUbahlokasirapid = new Response();
        try {
            if (lokasiRapidService.ubahLokasirapid(lokasiRapid)) {
                responseUbahlokasirapid.setBerhasil(true);
                responseUbahlokasirapid.setPesan("Berhasil Mengubah Lokasi Rapid");
            } else {
                responseUbahlokasirapid.setBerhasil(false);
                responseUbahlokasirapid.setPesan("Gagal Mengubah Lokasi Rapid");
            }
        } catch (Exception e) {
            responseUbahlokasirapid.setBerhasil(false);
            responseUbahlokasirapid.setPesan("Gagal Mengubah Lokasi Rapid");
        }

        return ResponseEntity.ok(
                responseUbahlokasirapid
        );
    }

    @DeleteMapping("/api/hapuslokasirapid/{id_lokasirapid}")
    public ResponseEntity<Object> hapusLokasirapid(@PathVariable int id_lokasirapid) {
        Response responseHapuslokasirapid = new Response();
        try {
            if (lokasiRapidService.hapusLokasirapid(id_lokasirapid)) {
                responseHapuslokasirapid.setBerhasil(true);
                responseHapuslokasirapid.setPesan("Berhasil Menghapus Lokasi Rapid");
            } else {
                responseHapuslokasirapid.setBerhasil(false);
                responseHapuslokasirapid.setPesan("Gagal Menghapus Lokasi Rapid");
            }
        } catch (Exception e) {
            responseHapuslokasirapid.setBerhasil(false);
            responseHapuslokasirapid.setPesan("Gagal Menghapus Lokasi Rapid");
        }

        return ResponseEntity.ok(
                responseHapuslokasirapid
        );
    }
}
