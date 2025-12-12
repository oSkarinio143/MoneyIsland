package pl.oskarinio.moneyisland.finance.Kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRegisteredEvent(Long id, String username, String email) {
}