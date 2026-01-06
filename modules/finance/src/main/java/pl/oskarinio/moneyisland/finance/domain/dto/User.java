package pl.oskarinio.moneyisland.finance.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class User {
    private Long id;
    @NonNull
    private Long authUserId;
    @NonNull
    private String username;

    private Long targetId;
}
