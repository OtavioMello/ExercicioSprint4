package br.compasso.politicalParty.dto;

import br.compasso.politicalParty.constants.Ideology;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoliticalPartyFormDTO {

    @NotEmpty(message = "Name field is required")
    private String name;
    @NotEmpty(message = "Acronym field is required")
    private String acronym;
    @NotNull(message = "Ideology field is required")
    private Ideology ideology;
    @NotNull(message = "Foundation date field is required")
    private Date foundationDate;

}
