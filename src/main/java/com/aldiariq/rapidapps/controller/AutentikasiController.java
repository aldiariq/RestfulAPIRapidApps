package com.aldiariq.rapidapps.controller;

import com.aldiariq.rapidapps.entity.KonfirmasiPendaftaran;
import com.aldiariq.rapidapps.entity.ResetPassword;
import com.aldiariq.rapidapps.entity.autentikasi.UbahPassword;
import com.aldiariq.rapidapps.response.Response;
import com.aldiariq.rapidapps.util.JwtUtil;
import com.aldiariq.rapidapps.entity.User;
import com.aldiariq.rapidapps.service.KonfirmasiPendaftaranService;
import com.aldiariq.rapidapps.service.ResetPasswordService;
import com.aldiariq.rapidapps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

import static java.util.Objects.isNull;

@RestController
public class AutentikasiController {
    @Autowired
    private UserService userService;
    @Autowired
    private KonfirmasiPendaftaranService konfirmasiPendaftaranService;
    @Autowired
    private ResetPasswordService resetPasswordService;
    @Autowired
    private JavaMailSender javaMailSender;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    private String BASE_URL = "http://127.0.0.1:8080/api/";

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AutentikasiController() throws MessagingException {
    }

    @PostMapping("/api/daftar")
    public ResponseEntity<Object> daftarUser(@ModelAttribute User user) throws MessagingException {
        Response responseDaftar = new Response();
        try {
            User dataUser = new User();
            dataUser.setEmailuser(user.getEmailuser());
            dataUser.setNamauser(user.getNamauser());
            dataUser.setPassworduser(bCryptPasswordEncoder.encode(user.getPassworduser()));
            dataUser.setStatususer("TIDAKAKTIF");
            dataUser.setAksesuser("USER");
            dataUser.setTgldibuatuser(user.getTgldibuatuser());
            dataUser.setTgldiupdateuser(user.getTgldiupdateuser());

            User dataDaftar = userService.daftarUser(dataUser);
            if (isNull(dataDaftar)) {
                responseDaftar.setBerhasil(false);
                responseDaftar.setPesan("Gagal Mendaftarkan User");
            } else {
                String kodeunik = bCryptPasswordEncoder.encode(user.getEmailuser()).replaceAll("/", "");
                KonfirmasiPendaftaran datakonfirmasiPendaftaran = new KonfirmasiPendaftaran();
                datakonfirmasiPendaftaran.setEmailkonfirmasipendaftaran(user.getEmailuser());
                datakonfirmasiPendaftaran.setKodeunikkonfirmasipendaftaran(kodeunik);
                datakonfirmasiPendaftaran.setIduser(dataDaftar.getIduser());

                KonfirmasiPendaftaran konfirmasiPendaftaran = konfirmasiPendaftaranService.tambahkonfirmasiPendaftaran(datakonfirmasiPendaftaran);
                if (isNull(konfirmasiPendaftaran)) {
                    responseDaftar.setBerhasil(false);
                    responseDaftar.setPesan("Gagal Mendaftarkan User");
                } else {
                    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

                    mimeMessageHelper.setTo(user.getEmailuser());
                    mimeMessageHelper.setSubject("Konfirmasi Pendaftaran");
                    mimeMessageHelper.setText("Klik disini untuk mengkonfirmasi pendaftaran " + "<a href='" + BASE_URL + "konfirmasipendaftaran/" + kodeunik + "'>Klik Disini</a>", true);
                    javaMailSender.send(mimeMessage);

                    responseDaftar.setBerhasil(true);
                    responseDaftar.setPesan("Berhasil Mendaftarkan User");
                }
            }
        } catch (Exception e) {
            responseDaftar.setBerhasil(false);
            responseDaftar.setPesan("Gagal Mendaftarkan User");
        }

        return ResponseEntity.ok(
                responseDaftar
        );
    }

    @PostMapping("/api/masuk")
    public ResponseEntity<Object> masukUser(@ModelAttribute User user) {
        Response responseMasuk = new Response();
        try {
            List<User> dataUser = userService.lihatUser();
            if (dataUser.size() > 0) {
                for (int i = 0; i < dataUser.size(); i++) {
                    if (dataUser.get(i).getEmailuser().equals(user.getEmailuser()) &&
                            bCryptPasswordEncoder.matches(user.getPassworduser(), dataUser.get(i).getPassworduser()) &&
                            dataUser.get(i).getStatususer().equals("AKTIF")
                    ) {
                        try {
                            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                                    dataUser.get(i).getEmailuser(), dataUser.get(i).getPassworduser()
                            ));
                        } catch (Exception e) {
                            responseMasuk.setBerhasil(false);
                            responseMasuk.setPesan("Gagal Masuk");
                        }
                        responseMasuk.setBerhasil(true);
                        responseMasuk.setPesan("Berhasil Masuk");
                        responseMasuk.setToken(jwtUtil.generateToken(dataUser.get(i).getEmailuser()));
                        responseMasuk.setUser(dataUser.get(i));
                    } else {
                        responseMasuk.setBerhasil(false);
                        responseMasuk.setPesan("Gagal Masuk");
                    }
                }
            } else {
                responseMasuk.setBerhasil(false);
                responseMasuk.setPesan("Gagal Masuk");
            }
        } catch (Exception e) {
            responseMasuk.setBerhasil(false);
            responseMasuk.setPesan("Gagal Masuk");
        }

        return ResponseEntity.ok(
                responseMasuk
        );
    }

    @PutMapping("/api/ubahpassword")
    public ResponseEntity<Object> ubahPassword(@ModelAttribute UbahPassword ubahPassword) {
        Response responseUbahpassword = new Response();
        try {
            User dataUser = userService.lihatUserbyId(ubahPassword.getIduser());
            String passwordenkripsi = bCryptPasswordEncoder.encode(ubahPassword.getPasswordbaru());

            if (bCryptPasswordEncoder.matches(ubahPassword.getPasswordlama(), dataUser.getPassworduser())) {
                if (userService.ubahpasswordUser(dataUser.getIduser(), passwordenkripsi)) {
                    responseUbahpassword.setBerhasil(true);
                    responseUbahpassword.setPesan("Berhasil Mengubah Password");
                } else {
                    responseUbahpassword.setBerhasil(false);
                    responseUbahpassword.setPesan("Gagal Mengubah Password");
                }
            } else {
                responseUbahpassword.setBerhasil(false);
                responseUbahpassword.setPesan("Gagal Mengubah Password");
            }
        } catch (Exception e) {
            responseUbahpassword.setBerhasil(false);
            responseUbahpassword.setPesan("Gagal Mengubah Password");
        }

        return ResponseEntity.ok(
                responseUbahpassword
        );
    }

    @PutMapping("/api/ubahprofil")
    public ResponseEntity<Object> ubahProfil(@ModelAttribute User user) {
        Response responseUbahprofil = new Response();
        try {
            if (userService.ubahprofilUser(user)) {
                responseUbahprofil.setBerhasil(true);
                responseUbahprofil.setPesan("Berhasil Mengubah Profil");
            } else {
                responseUbahprofil.setBerhasil(false);
                responseUbahprofil.setPesan("Gagal Mengubah Profil");
            }
        } catch (Exception e) {
            responseUbahprofil.setBerhasil(false);
            responseUbahprofil.setPesan("Gagal Mengubah Profil");
        }

        return ResponseEntity.ok(
                responseUbahprofil
        );
    }

    @PostMapping("/api/resetpassword")
    public ResponseEntity<Object> resetPassword(@ModelAttribute User user) throws MessagingException {
        Response responseresetPassword = new Response();
        try {
            String kodeunik = bCryptPasswordEncoder.encode(user.getEmailuser()).replaceAll("/", "");
            ;
            List<User> dataUser = userService.lihatUser();
            for (int i = 0; i < dataUser.size(); i++) {
                if (dataUser.get(i).getEmailuser().equals(user.getEmailuser())) {
                    ResetPassword dataresetPassword = new ResetPassword();
                    dataresetPassword.setEmailresetpassword(dataUser.get(i).getEmailuser());
                    dataresetPassword.setKodeunikresetpassword(kodeunik);
                    dataresetPassword.setIduser(dataUser.get(i).getIduser());
                    resetPasswordService.tambahresetPassword(dataresetPassword);

                    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
                    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

                    mimeMessageHelper.setTo(user.getEmailuser());
                    mimeMessageHelper.setSubject("Reset Password");
                    mimeMessageHelper.setText("Klik disini untuk mereset password " + "<a href='" + BASE_URL + "konfirmasiresetpassword/" + kodeunik + "'>Klik Disini</a>", true);
                    javaMailSender.send(mimeMessage);
                    responseresetPassword.setBerhasil(true);
                    responseresetPassword.setPesan("Behasil Mengirim Permintaan Reset Password");
                } else {
                    responseresetPassword.setBerhasil(false);
                    responseresetPassword.setPesan("Gagal Mengirim Permintaan Reset Password");
                }
            }
        } catch (Exception e) {
            responseresetPassword.setBerhasil(false);
            responseresetPassword.setPesan("Gagal Mengirim Permintaan Reset Password");
        }

        return ResponseEntity.ok(
                responseresetPassword
        );
    }

    @RequestMapping("/api/konfirmasiresetpassword/{kodeunik}")
    public ModelAndView konfirmasiresetPassword(@PathVariable String kodeunik) {
        List<ResetPassword> dataresetPassword = resetPasswordService.lihatresetPassword();
        String passwordenkripsi = bCryptPasswordEncoder.encode("12345678");
        ModelAndView modelAndView = new ModelAndView();
        for (int i = 0; i < dataresetPassword.size(); i++) {
            if (dataresetPassword.get(i).getKodeunikresetpassword().equals(kodeunik)) {
                if (
                        resetPasswordService.resetPassword(dataresetPassword.get(i).getIdresetpassword()) &&
                                userService.resetpasswordUser(dataresetPassword.get(i).getIduser(), passwordenkripsi)
                ) {
                    modelAndView.setViewName("/resetpassword/berhasil.html");
                    return modelAndView;
                }
            }
        }
        modelAndView.setViewName("/resetpassword/gagal.html");
        return modelAndView;
    }

    @RequestMapping("/api/konfirmasipendaftaran/{kodeunik}")
    public ModelAndView konfirmasiPendaftaran(@PathVariable String kodeunik) {
        List<KonfirmasiPendaftaran> datakonfirmasiPendaftaran = konfirmasiPendaftaranService.lihatkonfirmasiPendaftaran();
        ModelAndView modelAndView = new ModelAndView();
        for (int i = 0; i < datakonfirmasiPendaftaran.size(); i++) {
            if (datakonfirmasiPendaftaran.get(i).getKodeunikkonfirmasipendaftaran().equals(kodeunik)) {
                if (
                        konfirmasiPendaftaranService.konfirmasiPendaftaran(datakonfirmasiPendaftaran.get(i).getIdkonfirmasipendaftaran()) &&
                                userService.konfirmasiPendaftaran(datakonfirmasiPendaftaran.get(i).getIduser())
                ) {
                    modelAndView.setViewName("/pendaftaran/berhasil.html");
                    return modelAndView;
                }
            }
        }

        modelAndView.setViewName("/pendaftaran/gagal.html");
        return modelAndView;
    }


}
