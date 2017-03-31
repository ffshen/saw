package org.saw.servicebus.proxy.properties;
  
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyProperties {
	
	private Integer serverPort ;
	
	private Integer proxyPort ;
	
	private String proxyHost ;	
	

	public Integer getServerPort() {
		return serverPort;
	}

	public void setServerPort(Integer serverPort) {
		this.serverPort = serverPort;
	}

	public Integer getProxyPort() {
		return proxyPort;
	}

	public void setProxyPort(Integer proxyPort) {
		this.proxyPort = proxyPort;
	}

	public String getProxyHost() {
		return proxyHost;
	}

	public void setProxyHost(String proxyHost) {
		this.proxyHost = proxyHost;
	}
	
	
}
