package com.example.demo.controller.ui;

import com.example.demo.entity.IPFilterRule;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/ipfilter")
@AllArgsConstructor
public class IPFilterUIController {

    private final RestTemplate restTemplate;

    @GetMapping("/index")
    public String showFilterPage(Model model) {
        String url = "http://localhost:8080/api/ipfilter/rules";
        List<IPFilterRule> rules = Arrays.asList(restTemplate.getForObject(url, IPFilterRule[].class));
        model.addAttribute("rules", rules);
        return "ipfilter/index";
    }

    @PostMapping("/add")
    public String addRule(
            @RequestParam String sourceIPRange,
            @RequestParam String destinationIPRange,
            @RequestParam boolean allow) {
        IPFilterRule rule = new IPFilterRule(null, sourceIPRange, destinationIPRange, allow, null);
        String url = "http://localhost:8080/api/ipfilter/add";
        restTemplate.postForObject(url, rule, String.class);
        return "redirect:/ipfilter";
    }

    @PostMapping("/remove")
    public String removeRule(@RequestParam Long ruleId) {
        String url = "http://localhost:8080/api/ipfilter/remove/" + ruleId;
        restTemplate.postForEntity(url, null, String.class);
        return "redirect:/ipfilter";
    }

    @PostMapping("/check")
    public String checkIp(
            @RequestParam String sourceIP,
            @RequestParam String destinationIP,
            Model model) {
        String url = "http://localhost:8080/api/ipfilter/check?sourceIP=" + sourceIP + "&destinationIP=" + destinationIP;
        Boolean allowed = restTemplate.postForObject(url, null, Boolean.class);
        model.addAttribute("allowed", allowed);
        model.addAttribute("sourceIP", sourceIP);
        model.addAttribute("destinationIP", destinationIP);
        model.addAttribute("rules", restTemplate.getForObject("http://localhost:8080/api/ipfilter/rules", IPFilterRule[].class));
        return "ipfilter/index";
    }
}

