package com.example.nobsv2.product.headers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {
    @GetMapping("/header")
    public String getRegionalResponse(@RequestHeader(required = false, defaultValue = "US") String region) {
        return switch (region) {
            case "US" -> "BALD EAGLE FREEDOM";
            case "CAN" -> "LIL PLANTS, COLD AND ROBIN FROM HIMYM";
            case "BR" -> "SAMBA, CAIPIRINHA AND CORRUPTION";
            default -> "Country not supported";
        };

    }
}
