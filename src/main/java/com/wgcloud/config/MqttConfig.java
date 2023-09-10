package com.wgcloud.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("mqtt")
public class MqttConfig {
  private String userName;
  private String password;
  private String brokerUrl;
  private String minTaskTopic;

  public String getMinTaskTopic() {
    return minTaskTopic;
  }

  public void setMinTaskTopic(String minTaskTopic) {
    this.minTaskTopic = minTaskTopic;
  }

  public String getBrokerUrl() {
    return brokerUrl;
  }

  public void setBrokerUrl(String brokerUrl) {
    this.brokerUrl = brokerUrl;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }
}
