package com.enigma.MajuMundur.controller;

import com.enigma.MajuMundur.constant.APIUrl;
import com.enigma.MajuMundur.entity.Merchant;
import com.enigma.MajuMundur.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = APIUrl.MERCHANT_API)
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping
    public List<Merchant> getAllMerchants() {
        return merchantService.getAllMerchants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Merchant> getMerchantById(@PathVariable String id) {
        Optional<Merchant> merchant = merchantService.getMerchantById(id);
        return merchant.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Merchant createMerchant(@RequestBody Merchant merchant) {
        return merchantService.saveMerchant(merchant);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Merchant> updateMerchant(@PathVariable String id, @RequestBody Merchant merchantDetails) {
        Optional<Merchant> merchant = merchantService.getMerchantById(id);
        if (merchant.isPresent()) {
            merchantDetails.setId(id);
            return ResponseEntity.ok(merchantService.saveMerchant(merchantDetails));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMerchant(@PathVariable String id) {
        if (merchantService.getMerchantById(id).isPresent()) {
            merchantService.deleteMerchant(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

