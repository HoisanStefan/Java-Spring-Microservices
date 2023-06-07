package com.awbd2.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCardRequest {

    private String cardNumber;

    @NotNull
    @NotBlank
    @Size(max=3)
    private String cvv;
}
