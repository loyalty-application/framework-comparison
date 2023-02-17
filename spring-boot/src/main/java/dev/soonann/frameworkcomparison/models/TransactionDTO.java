package dev.soonann.frameworkcomparison.models;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    
    private List<RequestDTO> requests;
}
