package pl.oskarinio.moneyisland.shared.kafka;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDeletedEvent(Long id, String username) {
}
