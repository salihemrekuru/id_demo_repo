package com.example.demo.service;

import com.example.demo.entity.IPFilterRule;
import com.example.demo.exceptions.exception.ResourceNotFoundException;
import com.example.demo.repository.IPFilterRuleRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IPFilterService {

    private final IPFilterRuleRepository ipFilterRuleRepository;

    public IPFilterRule addRule(IPFilterRule rule) {
        return ipFilterRuleRepository.save(rule);
    }

    public void removeRule(Long id) {
        if (!ipFilterRuleRepository.existsById(id)) {
            throw new ResourceNotFoundException("Rule not found with id: " + id);
        }
        ipFilterRuleRepository.deleteById(id);
    }

    public List<IPFilterRule> getAllRules() {
        return ipFilterRuleRepository.findAll();
    }

    public boolean checkIpPair(String sourceIP, String destinationIP) {
        // Kontrol algoritması buraya eklenebilir
        // Örnek: Veritabanındaki kurallarla karşılaştırma yapılabilir
        return false;
    }
}
