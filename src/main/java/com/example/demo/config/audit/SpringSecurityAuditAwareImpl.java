package com.example.demo.config.audit;

import java.util.Optional;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.data.domain.AuditorAware;
import springfox.documentation.spi.service.contexts.SecurityContext;

public class SpringSecurityAuditAwareImpl implements AuditorAware<Long> {

  @Override
  public Optional<Long> getCurrentAuditor() {
    return Optional.of(new Long(1));
  }
}
