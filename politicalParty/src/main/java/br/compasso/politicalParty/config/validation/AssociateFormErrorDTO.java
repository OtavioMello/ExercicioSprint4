package br.compasso.politicalParty.config.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociateFormErrorDTO {

    private String field;
    private String message;

}
