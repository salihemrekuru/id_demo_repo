package com.example.demo.controller.backend;

import com.example.demo.entity.IPFilterRule;
import com.example.demo.service.IPFilterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ipfilter")
public class IPFilterController {

    private final IPFilterService ipFilterService;

    public IPFilterController(IPFilterService ipFilterService) {
        this.ipFilterService = ipFilterService;
    }

    @GetMapping("/rules")
    public List<IPFilterRule> getAllRules() {
        return ipFilterService.getAllRules();
    }

    @PostMapping("/rules")
    public ResponseEntity<String> addRule(@RequestBody IPFilterRule rule) {
        ipFilterService.addRule(rule);
        return ResponseEntity.ok("Rule added successfully");
    }

    @DeleteMapping("/rules/{ruleId}")
    public ResponseEntity<String> removeRule(@PathVariable Long ruleId) {
        ipFilterService.removeRule(ruleId);
        return ResponseEntity.ok("Rule removed successfully");
    }

    @PostMapping("/check")
    public ResponseEntity<Boolean> checkIpPair(@RequestParam String sourceIP, @RequestParam String destinationIP) {
        boolean allowed = ipFilterService.checkIpPair(sourceIP, destinationIP);
        return ResponseEntity.ok(allowed);
    }
}
