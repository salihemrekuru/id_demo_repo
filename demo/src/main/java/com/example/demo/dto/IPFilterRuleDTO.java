package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IPFilterRuleDTO {
    @NotNull
    private String sourceIPRange;
    @NotNull
    private String destinationIP;
    @NotNull
    private boolean allow;
}
