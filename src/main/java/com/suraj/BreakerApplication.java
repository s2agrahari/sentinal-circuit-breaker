package com.suraj;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
public class BreakerApplication {

  public static void main(String[] args) {
    SpringApplication.run(BreakerApplication.class, args);
    DegradeRuleManager.loadRules(
        Collections.singletonList(
            new DegradeRule("readingList") // resource name
                .setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_RATIO) // strategy
                .setCount(0.5) // threshold
                .setTimeWindow(10) // circuit breaking timeout (in second)
            ));
  }

  @Bean
  RestTemplate restTemplate() {
    return new RestTemplateBuilder().build();
  }

  @Bean
  public SentinelResourceAspect sentinelResourceAspect() {
    return new SentinelResourceAspect();
  }
}
