package com.example.demo.dto;

import com.example.demo.validation.ValidIpRange;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IPFilterRuleCreateDTO {
    @NotNull(message = "Source IP range cannot be null")
    @ValidIpRange(message = "Invalid source IP range format")
    private String sourceIpRange;

    @NotNull(message = "Destination IP range cannot be null")
    @ValidIpRange(message = "Invalid destination IP range format")
    private String destinationIpRange;

    @NotNull(message = "Allow/deny status cannot be null")
    private boolean allow;  // true: allow, false: deny
}
