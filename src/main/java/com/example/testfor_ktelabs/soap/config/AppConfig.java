package com.example.testfor_ktelabs.soap.config;

import com.example.testfor_ktelabs.soap.soap.SoapSrv;
import com.example.testfor_ktelabs.soap.soap.impl.SoapSrvImpl;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class AppConfig {
    @Autowired
    private  Bus bus;

    @Bean
    SoapSrv getUserSrv() {
        return new SoapSrvImpl();
    }

    @Bean
    public Endpoint userEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, getUserSrv());
        endpoint.publish("/shop/");
        return endpoint;
    }
}
