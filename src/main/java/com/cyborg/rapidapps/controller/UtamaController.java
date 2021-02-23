package com.cyborg.rapidapps.controller;

import com.cyborg.rapidapps.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UtamaController {
    @GetMapping("/api/")
    public ResponseEntity<Object> index() {
        Response responseUtama = new Response();
        responseUtama.setBerhasil(true);
        responseUtama.setPesan("Endpoint Utama Restfulapi Rapid Apps");

        return ResponseEntity.ok(
                responseUtama
        );
    }
}
