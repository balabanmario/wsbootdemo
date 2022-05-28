package com.example.wsbootdemo.mycode;

import com.example.wsbootdemo.autoconfig.beans.HelloProperties;
import com.example.wsbootdemo.autoconfig.beans.HelloService;
import com.example.wsbootdemo.exceptions.InvalidHelloPrefixException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {


    @Bean
    public HelloService myHelloService(@Autowired HelloProperties helloProperties) {
        return new HelloService() {
            @Override
            public void sayHello(String name) {
                Logger logger = LoggerFactory.getLogger(this.getClass());
                logger.info("This is my own implementation of Hello service with name: " + name+" with prefix:"+helloProperties.getPrefix());

                //                throw new InvalidHelloPrefixException("tes");
            }
        };
    }
}
