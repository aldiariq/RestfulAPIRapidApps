package com.cyborg.rapidapps.service;

import com.cyborg.rapidapps.entity.User;
import com.cyborg.rapidapps.repository.ResetPasswordRepository;
import com.cyborg.rapidapps.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    private ResetPasswordRepository resetPasswordRepository;

    public User daftarUser(User user) {
        return userRepository.save(user);
    }

    public List<User> lihatUser() {
        return userRepository.findAll();
    }

    public User lihatUserbyId(int iduser) {
        return userRepository.findById(iduser).orElse(null);
    }

    public boolean konfirmasiPendaftaran(int iduser) {
        User userSekarang = userRepository.findById(iduser).orElse(null);
        if (isNull(userSekarang)) {
            return false;
        } else {
            userSekarang.setStatususer("AKTIF");
            userRepository.save(userSekarang);
            return true;
        }
    }

    public boolean resetpasswordUser(int iduser, String passwordenkripsi) {
        User userSekarang = userRepository.findById(iduser).orElse(null);
        if (isNull(userSekarang)) {
            return false;
        } else {
            userSekarang.setPassworduser(passwordenkripsi);
            userRepository.save(userSekarang);
            return true;
        }
    }

    public boolean ubahprofilUser(User user) {
        User userSekarang = userRepository.findById(user.getIduser()).orElse(null);
        if (isNull(userSekarang)) {
            return false;
        } else {
            userSekarang.setEmailuser(user.getEmailuser());
            userSekarang.setNamauser(user.getNamauser());
            userSekarang.setTgldiupdateuser(user.getTgldiupdateuser());
            userRepository.save(userSekarang);
            return true;
        }
    }

    public boolean ubahpasswordUser(int iduser, String passwordenkripsi) {
        User userSekarang = userRepository.findById(iduser).orElse(null);
        if (isNull(userSekarang)) {
            return false;
        } else {
            userSekarang.setPassworduser(passwordenkripsi);
            userRepository.save(userSekarang);
            return true;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> dataUser = lihatUser();
        User user = null;
        for (int i = 0; i < dataUser.size(); i++) {
            if (dataUser.get(i).getEmailuser().equals(s)){
                user = dataUser.get(i);
            }
        }

        return new org.springframework.security.core.userdetails.User(user.getEmailuser(), user.getPassworduser(), new ArrayList<>());
    }
}
