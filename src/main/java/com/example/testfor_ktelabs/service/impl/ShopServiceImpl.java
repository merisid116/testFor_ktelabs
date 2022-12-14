package com.example.testfor_ktelabs.service.impl;

import com.example.testfor_ktelabs.dto.ProductBuyDto;
import com.example.testfor_ktelabs.dto.ProductRegisterSell;
import com.example.testfor_ktelabs.entity.Position;
import com.example.testfor_ktelabs.entity.Sale;
import com.example.testfor_ktelabs.repositories.PositionsRepository;
import com.example.testfor_ktelabs.repositories.ProductRepository;
import com.example.testfor_ktelabs.repositories.SaleRepository;
import com.example.testfor_ktelabs.repositories.UserRepository;
import com.example.testfor_ktelabs.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {
    private final SaleRepository saleRepository;
    private final PositionsRepository positionsRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;


    @Override
    public long getTotalCost(List<ProductBuyDto> productBuyDtoList, Long userId) {
        List<Position> positions = new ArrayList<>();
        AtomicReference<Double> result = getResult(productBuyDtoList, userId, positions);
        if (!positions.isEmpty()) {
            Sale sale = new Sale();
            sale.setRegister(false);
            sale.setUserId(userId);
            sale.setPositions(positions);
            saleRepository.save(sale);
            positions.forEach(position -> position.setSale(sale));
            positionsRepository.saveAll(positions);
        }
        return (long) (result.get() * 100);
    }


    @Override
    public String registerSell(ProductRegisterSell productRegisterSell, Long userId) {
        AtomicReference<String> result = new AtomicReference<>("");
        saleRepository.findAllByUserIdAndRegisterFalse(userId).ifPresent(sale -> {
            double totalPrice = positionsRepository.getTotalPrice(sale);
            if (totalPrice == productRegisterSell.getTotalPrice() / 100) {
                generateCheckNumber(sale);
                sale.setRegister(true);
                sale.setDateOfSale(LocalDate.now());
                saleRepository.save(sale);
                result.set(String.format("%05d", sale.getCheckNumber()));
            } else result.set("Error");
        });
        return result.get();
    }

    private void generateCheckNumber(Sale sale) {
        Optional<Long> lastSale = saleRepository.getLastSale();
        lastSale.ifPresent(s -> {
        });
        if (lastSale.isPresent()) {
            Long s = lastSale.get();
            sale.setCheckNumber(++s);
        }else sale.setCheckNumber(100L);
    }

    private AtomicReference<Double> getResult(List<ProductBuyDto> productBuyDtoList, Long userId, List<Position> positions) {
        AtomicReference<Double> result = new AtomicReference<>(0.0);
        for (ProductBuyDto productBuy : productBuyDtoList) {
            Long productId = productBuy.getProductId();
            Long amount = productBuy.getAmount();

            productRepository.findById(productId).ifPresent(product -> {
                userRepository.findById(userId).ifPresent(user -> {
                    double totalPrice = 0.0;
                    double discount = product.getDiscount();
                    if (amount > 5) {
                        discount += user.getSecondDiscount();
                    } else {
                        discount += user.getFirstDiscount();
                    }
                    if (discount > 18.00) discount = 18.00;
                    totalPrice = product.getPrice() * amount - ((product.getPrice() / 100) * discount);

                    Position position = new Position();
                    position.setProductId(productId);
                    position.setAmount(amount);
                    position.setOriginalPrice(product.getPrice() * amount);
                    position.setFinalPrice(totalPrice);
                    position.setFinalDiscount(discount);
                    positions.add(position);
                    Double aDouble = result.get();
                    result.set(aDouble + (totalPrice));
                });
            });
        }
        return result;
    }
}
