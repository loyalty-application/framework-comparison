package dev.soonann.frameworkcomparison.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    
    private String id;

    private String transaction_id;

    private String merchant;

    private String mcc;

    private String currency;

    private Double amount;

    private String transaction_date;

    private String card_id;

    private String card_pan;

    private String card_type;
    
}
