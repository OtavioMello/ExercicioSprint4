package br.compasso.politicalParty.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociatePartyDTO {

    private Long associateId;
    private Long politicalPartyId;

}
