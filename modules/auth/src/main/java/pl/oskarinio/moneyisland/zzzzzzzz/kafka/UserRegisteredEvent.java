package pl.oskarinio.moneyisland.zzzzzzzz.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserRegisteredEvent(Long id, String username, String email) {
}