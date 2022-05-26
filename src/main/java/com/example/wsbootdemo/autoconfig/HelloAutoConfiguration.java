package com.example.wsbootdemo.autoconfig;

import com.example.wsbootdemo.autoconfig.beans.ConsoleHelloService;
import com.example.wsbootdemo.autoconfig.beans.HelloProperties;
import com.example.wsbootdemo.autoconfig.beans.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(HelloService.class)
@EnableConfigurationProperties(HelloProperties.class)
public class HelloAutoConfiguration {

	private final HelloProperties properties;

	public HelloAutoConfiguration(HelloProperties properties) {
		this.properties = properties;
	}

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "hello", value = "prefix")
	public HelloService helloService() {
		return new ConsoleHelloService(this.properties.getPrefix(),
				this.properties.getSuffix());
	}

}