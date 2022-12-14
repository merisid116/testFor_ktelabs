package com.example.testfor_ktelabs.soap.soap;

import com.example.testfor_ktelabs.dto.ProductBuyDto;
import com.example.testfor_ktelabs.dto.ProductDto;
import com.example.testfor_ktelabs.dto.ProductExtendInfo;
import com.example.testfor_ktelabs.dto.ProductRegisterSell;
import com.example.testfor_ktelabs.entity.BaseStats;
import com.example.testfor_ktelabs.entity.User;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.List;

@WebService
@SOAPBinding(style= SOAPBinding.Style.RPC)
 public interface SoapSrv {

    @WebMethod(action="getAllUser")
    List<User> getAllUser();

    @WebMethod(action="updateUser")
    void updateUser(@WebParam(name = "userId") Long userId,
                    @WebParam(name = "firstDiscount")Double firstDiscount,
                    @WebParam(name = "secondDiscount")Double secondDiscount);

    @WebMethod(action="getAllProducts")
    List<ProductDto> getAllProducts();

   @WebMethod(action="getProducts")
   ProductExtendInfo getProducts(@WebParam(name = "productId")Long id,
                                 @WebParam(name = "userId") Long userId);

    @WebMethod(action="gradeProduct")
    void gradeProduct(@WebParam(name = "userId") Long userId,
                      @WebParam(name = "productId")Long productId,
                      @WebParam(name = "grade")Integer grade);

    @WebMethod(action="getTotalCost")
    Long getTotalCost(List<ProductBuyDto> productBuyDto,
                      @WebParam(name = "userId") Long userId);

    @WebMethod(action="registerSell")
    String registerSell(ProductRegisterSell productRegisterSell,
                        @WebParam(name = "userId") Long userId);

    @WebMethod(action="getStats")
    BaseStats getStats(@WebParam(name = "userId") Long userId,
                       @WebParam(name = "productId")Long productId);
}
