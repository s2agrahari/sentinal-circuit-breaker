package com.suraj;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class BookService {

  private final RestTemplate restTemplate;

  public BookService(RestTemplate rest) {
    this.restTemplate = rest;
  }

  @SentinelResource(value = "readingList", fallback = "reliable")
  public String readingList() {
    URI uri = URI.create("https://currencyapi.net/api/v1/rates?key=demo");
    return this.restTemplate.getForObject(uri, String.class);
  }

  @Bean("reliable")
  public String reliable() {
    return "Api Hit Failed";
  }
}
