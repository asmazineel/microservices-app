package org.ecom.customerservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RefreshScope
public class ConfigTestRestController {
    @Value("${global.params.p1}")
    private String p1;

    @Value("${global.params.p2}")
    private String p2;

    private final CustomerConfigParams customerConfigParams;

    @GetMapping("/testConfig1")
    Map<String, String> getConfig() {
        return Map.of("p1", p1, "p2", p2);
    }

    @GetMapping("/testConfig2")
    CustomerConfigParams getConfig2() {
        return customerConfigParams;
    }
}
