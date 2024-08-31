package com.example.demo.service;

import com.example.demo.entity.IPFilterRule;
import com.example.demo.repository.IPFilterRuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class IPFilterService {

    private final IPFilterRuleRepository ipFilterRuleRepositor;

    public IPFilterRule addRule(IPFilterRule rule) {
        return ipFilterRuleRepositor.save(rule);
    }

    public void removeRule(Long id) {
        ipFilterRuleRepositor.deleteById(id);
    }

    public List<IPFilterRule> getAllRules() {
        return ipFilterRuleRepositor.findAll();
    }

    // IP adres çiftini kontrol eden bir yöntem eklenebilir
    public boolean checkIpPair(String sourceIP, String destinationIP) {
        // Kontrol algoritması buraya eklenebilir
        return false;
    }
}