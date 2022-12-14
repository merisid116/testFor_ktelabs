package com.example.testfor_ktelabs.soap.soap.impl;

import com.example.testfor_ktelabs.dto.ProductBuyDto;
import com.example.testfor_ktelabs.dto.ProductDto;
import com.example.testfor_ktelabs.dto.ProductExtendInfo;
import com.example.testfor_ktelabs.dto.ProductRegisterSell;
import com.example.testfor_ktelabs.entity.BaseStats;
import com.example.testfor_ktelabs.entity.User;
import com.example.testfor_ktelabs.service.ProductService;
import com.example.testfor_ktelabs.service.ShopService;
import com.example.testfor_ktelabs.service.StatisticsService;
import com.example.testfor_ktelabs.service.UserService;
import com.example.testfor_ktelabs.soap.soap.SoapSrv;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(targetNamespace = "http://ws.service.com/",
        portName = "SoapSrvImpl",
        serviceName = "SoapSrvImpl",
        endpointInterface = "com.example.testfor_ktelabs.soap.soap.SoapSrv")
public class SoapSrvImpl implements SoapSrv {

    @Autowired
    private UserService userService;
    @Autowired
    private ShopService shopService;
    @Autowired
    private ProductService productService;
    @Autowired
    private StatisticsService statisticsService;

    @Override
    public List<User> getAllUser() {
        List<User> all = userService.findAll();
        return all;
    }

    @Override
    public void updateUser(Long userId, Double firstDiscount, Double secondDiscount) {
        userService.updateUser(userId, firstDiscount, secondDiscount);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @Override
    public ProductExtendInfo getProducts(Long id, Long userId) {
        return productService.getExtendProductInfo(id, userId);
    }

    @Override
    public void gradeProduct(Long userId, Long productId, Integer grade) {
        productService.gradeProduct(userId, productId, grade);
    }

    @Override
    public Long getTotalCost(List<ProductBuyDto> list, Long userId) {
        return shopService.getTotalCost(new ArrayList<>(), userId);
    }

    @Override
    public String registerSell(ProductRegisterSell productRegisterSell, Long userId) {
        return shopService.registerSell(productRegisterSell, userId);
    }

    @Override
    public BaseStats getStats(Long userId, Long productId) {
        return statisticsService.getStats(userId, productId);
    }
}
