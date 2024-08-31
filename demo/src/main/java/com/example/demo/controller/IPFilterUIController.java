package com.example.demo.controller;

import com.example.demo.dto.ErrorResponse;
import com.example.demo.dto.IPFilterRuleCreateDTO;
import com.example.demo.entity.IPFilterRule;
import com.example.demo.mapper.IPFilterRuleMapper;
import com.example.demo.service.IPFilterService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/ipfilter")
@AllArgsConstructor
public class IPFilterUIController {

    private final IPFilterService ipFilterService;
    private final IPFilterRuleMapper ipFilterRuleMapper;

    @GetMapping("/index")
    public String showFilterPage(Model model) {
        model.addAttribute("rules", ipFilterService.getAllRules());
        return "ipfilter/index";
    }
    @PostMapping("/add")
    public RedirectView addRule(
            @Valid @ModelAttribute("rule") IPFilterRuleCreateDTO ruleDTO, BindingResult result) {
        RedirectView redirectView;
        if (!result.hasErrors()) {
            IPFilterRule ipFilterRule = ipFilterRuleMapper.toEntity(ruleDTO);
            IPFilterRule savedRule = ipFilterService.addRule(ipFilterRule);
            // TODO hata durumunu kontrol et
            ipFilterService.addRule(ipFilterRule);
            redirectView = new RedirectView(  "/rules" + "?addSuccessful", true);
        } else {
            List<String> errorMessages = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList());
            redirectView = new RedirectView(  "/addrule" + new ErrorResponse("Validation failed", errorMessages), true);
        }
        return redirectView;
    }

    // TODO deleteMapping e cevir
    @GetMapping("/remove")
    public RedirectView removeRule(@RequestParam Long ruleId) {
        ipFilterService.removeRule(ruleId);
        // TODO hata durumunu kontrol et
        return new RedirectView("/rules" + "?deleteSuccessful", true);
    }

    @PostMapping("/check-ip")
    public RedirectView checkIp(
            @RequestParam String sourceIP,
            @RequestParam String destinationIP,
            Model model) {
        // TODO
        return  new RedirectView("/checkrule" + "?checkedBurayiDegistir", true);
    }


    @GetMapping("/check-ip")
    public String checkIpView(
            @RequestParam String sourceIP,
            @RequestParam String destinationIP,
            Model model) {
        // TODO model düzenlenecek
        return "checkrule";
    }
}

