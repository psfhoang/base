package com.example.demo.config.audit;

import java.time.ZonedDateTime;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "dateTimeProvider")
public class AuditingConfig {

  @Bean(name = "auditorProvider")
  public AuditorAware<Long> auditorProvider() {
    return new SpringSecurityAuditAwareImpl();
  }

  @Bean(name = "dateTimeProvider")
  public DateTimeProvider dateTimeProvider() {
    return () -> Optional.of(ZonedDateTime.now().toLocalDateTime());
  }

}
