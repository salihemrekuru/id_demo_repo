package com.example.demo.controller.backend;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.dto.IPFilterRuleCreateDTO;
import com.example.demo.entity.IPFilterRule;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.IPFilterRuleMapper;
import com.example.demo.service.IPFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/ipfilter")
@RequiredArgsConstructor
public class IPFilterController {

    private final IPFilterService ipFilterService;
    private final IPFilterRuleMapper ipFilterRuleMapper;

    @GetMapping("/rules")
    public ResponseEntity<List<IPFilterRule>> getAllRules() {
        List<IPFilterRule> rules = ipFilterService.getAllRules();
        return ResponseEntity.ok(rules);
    }


    @PostMapping("/rules")
    public ResponseEntity<?> addRule(@Valid @RequestBody IPFilterRuleCreateDTO ruleDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(new ErrorResponse("Validation failed", errorMessages));
        }

        IPFilterRule rule = ipFilterRuleMapper.toEntity(ruleDTO);
        IPFilterRule savedRule = ipFilterService.addRule(rule);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedRule);
    }

    @DeleteMapping("/rules/{ruleId}")
    public ResponseEntity<String> removeRule(@PathVariable Long ruleId) {
        try {
            ipFilterService.removeRule(ruleId);
            return ResponseEntity.ok("Rule removed successfully");
        } catch (ResourceNotFoundException e) {
            throw e; // Bu istisna global exception handler tarafından ele alınır
        }
    }

    @PostMapping("/check")
    public ResponseEntity<Boolean> checkIpPair(@RequestParam String sourceIP, @RequestParam String destinationIP) {
        boolean allowed = ipFilterService.checkIpPair(sourceIP, destinationIP);
        return ResponseEntity.ok(allowed);
    }
}
