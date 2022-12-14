package com.example.testfor_ktelabs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class ProductRegisterSell {
    private List<ProductBuyDto> productBuyDtoList;
    private Long totalPrice;

}
