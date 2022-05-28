package com.example.wsbootdemo.autoconfig.beans;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("hello")
public class HelloProperties {

	/**
	 * Prefix of the welcome message. A space is added before the prefix and
	 * the actual message.
	 */
	private String prefix;

	/**
	 * Suffix of the welcome message.
	 */
	private String suffix = "!";

	private boolean myaop = false;

	public boolean isMyaop() {
		return myaop;
	}

	public void setMyaop(boolean myaop) {
		this.myaop = myaop;
	}


	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

}