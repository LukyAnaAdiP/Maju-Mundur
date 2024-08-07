package com.enigma.MajuMundur.service;

import com.enigma.MajuMundur.entity.Merchant;
import com.enigma.MajuMundur.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;

    public List<Merchant> getAllMerchants() {
        return merchantRepository.findAll();
    }

    public Optional<Merchant> getMerchantById(String id) {
        return merchantRepository.findById(id);
    }

    public Merchant saveMerchant(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    public void deleteMerchant(String id) {
        merchantRepository.deleteById(id);
    }
}
