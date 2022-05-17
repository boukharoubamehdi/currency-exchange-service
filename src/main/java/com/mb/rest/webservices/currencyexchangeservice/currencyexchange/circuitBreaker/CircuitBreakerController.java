package com.mb.rest.webservices.currencyexchangeservice.currencyexchange.circuitBreaker;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

  private final Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

  @GetMapping("/sample-api")
  // we will use the default configuration for the retry.
  // by default, @Retry, what it does is if there is any failure, if there is any failure in the
  // execution of this specific method.
  // So, in this method, when it's executing, if there's an exception, what would happen is, it
  // would be  retried thrice.
  // and if the retry fails all the three times, only then it would return an error back.
  // @Retry(name = "default")
  @Retry(name = "sample-api", fallbackMethod = "hardCodedResponse")
  public String sampleApi() {
    // let's make this fail and then, let's focus on retry.
    // So, what I would do is, I would actually create a new RestTemplate and let's just call some
    // dummy API.

    logger.info("Sample Api received call");
    ResponseEntity<String> forEntity =
        new RestTemplate().getForEntity("http://localhost:8000/some-dummy-url", String.class);
    return forEntity.getBody();
  }

  // We can have different fallback methods for different kinds of exceptions.
  // Let's have a blanket method capturing everything, Exception ex
  // Exception ex in this code is meant to call all the exceptions that can be thrown
  private String hardCodedResponse(Exception ex) {
    logger.info("Fall back response");
    return "fallback-response";
  }
  // we looked at the retry features which are present in
  // resilience4j. These are useful when a service is momentarily down. You'd just give the service
  // a little of time and then call it.
}
