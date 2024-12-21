package com.example.nobsv2.product.headers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeaderController {
    @GetMapping("/header")
    public String getRegionalResponse(@RequestHeader(required = false, defaultValue = "US") String region) {
        if(region.equals("US")) return "BALD EAGLE FREEDOM";

        if(region.equals("CAN")) return "LIL PLANTS COLD AND ROBIN FROM HIMYM";

        if(region.equals("BR")) return "SAMBA, BITCHES AND CORRUPTION";

        return "Country not supported";
    }
}
