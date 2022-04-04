package br.compasso.politicalParty.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociatePartyFormDTO {

    @NotNull(message = "Associate ID field is required")
    private Long associateId;
    @NotNull(message = "Political Party ID field is required")
    private Long politicalPartyId;

}
