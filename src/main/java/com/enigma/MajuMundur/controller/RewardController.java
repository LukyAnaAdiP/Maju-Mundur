package com.enigma.MajuMundur.controller;

import com.enigma.MajuMundur.constant.APIUrl;
import com.enigma.MajuMundur.entity.Reward;
import com.enigma.MajuMundur.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = APIUrl.REWARD_API)
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping
    public List<Reward> getAllRewards() {
        return rewardService.getAllRewards();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reward> getRewardById(@PathVariable String id) {
        Optional<Reward> reward = rewardService.getRewardById(id);
        return reward.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Reward createReward(@RequestBody Reward reward) {
        return rewardService.saveReward(reward);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Reward> updateReward(@PathVariable String id, @RequestBody Reward rewardDetails) {
        Optional<Reward> reward = rewardService.getRewardById(id);
        if (reward.isPresent()) {
            rewardDetails.setId(id);
            return ResponseEntity.ok(rewardService.saveReward(rewardDetails));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReward(@PathVariable String id) {
        if (rewardService.getRewardById(id).isPresent()) {
            rewardService.deleteReward(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}