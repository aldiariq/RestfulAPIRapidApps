package com.aldiariq.rapidapps.service;

import com.aldiariq.rapidapps.entity.ResetPassword;
import com.aldiariq.rapidapps.repository.ResetPasswordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResetPasswordService {
    @Autowired
    private ResetPasswordRepository resetPasswordRepository;

    public ResetPassword tambahresetPassword(ResetPassword resetPassword){
        return resetPasswordRepository.save(resetPassword);
    }

    public boolean resetPassword(int idresetpassword){
        if (resetPasswordRepository.existsById(idresetpassword)){
            resetPasswordRepository.deleteById(idresetpassword);
            return true;
        }else {
            return false;
        }
    }

    public List<ResetPassword> lihatresetPassword(){
        return resetPasswordRepository.findAll();
    }
}
