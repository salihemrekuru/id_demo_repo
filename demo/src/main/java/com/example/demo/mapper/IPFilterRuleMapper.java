package com.example.demo.mapper;

import com.example.demo.dto.IPFilterRuleCreateDTO;
import com.example.demo.entity.IPFilterRule;
import org.springframework.stereotype.Component;

@Component
public class IPFilterRuleMapper {

    public IPFilterRule toEntity(IPFilterRuleCreateDTO dto) {
        IPFilterRule rule = new IPFilterRule();
        rule.setSourceIpRange(dto.getSourceIpRange());
        rule.setDestinationIpRange(dto.getDestinationIpRange());
        rule.setAllow(dto.isAllow());
        return rule;
    }
}
