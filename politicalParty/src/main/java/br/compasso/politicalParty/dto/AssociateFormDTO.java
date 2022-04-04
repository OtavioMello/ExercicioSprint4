package br.compasso.politicalParty.dto;

import br.compasso.politicalParty.constants.Gender;
import br.compasso.politicalParty.constants.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociateFormDTO {

    @NotEmpty(message = "Name field is required")
    private String name;
    @NotNull(message = "Role field is required")
    private Position role;
    @NotNull(message = "Birth field date is required")
    private Date birthDate;
    @NotNull(message = "Gender field is required")
    private Gender gender;
}
