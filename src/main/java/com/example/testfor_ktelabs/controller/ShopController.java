package com.example.testfor_ktelabs.controller;

import com.example.testfor_ktelabs.dto.ProductBuyDto;
import com.example.testfor_ktelabs.dto.ProductRegisterSell;
import com.example.testfor_ktelabs.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static com.example.testfor_ktelabs.constants.Constants.Headers.USER_ID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;


    @PostMapping("/price")
    public long getTotalCost(@RequestBody List<ProductBuyDto> productBuyDtoList,
                             @RequestHeader(value = USER_ID) Long userId) {
        return shopService.getTotalCost(productBuyDtoList, userId);
    }

    @PostMapping("/sell")
    public String registerSell(@RequestBody ProductRegisterSell productRegisterSell,
                               @RequestHeader(value = USER_ID) Long userId) {
        return shopService.registerSell(productRegisterSell, userId);
    }
}
