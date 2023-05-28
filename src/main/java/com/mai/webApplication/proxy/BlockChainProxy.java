package com.mai.webApplication.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "blockChain",
            url = "${blockChain.service.url}")
@RequestMapping("/chain")
public interface BlockChainProxy {

    @PostMapping("/create-account")
    ResponseEntity<String> createAccount(@RequestBody String seed);

    @PostMapping("/push-data")
    ResponseEntity<String> pushData(@RequestBody String token);

    @GetMapping("/get-data")
    ResponseEntity<String> getData(@RequestBody String tokenId);
}
