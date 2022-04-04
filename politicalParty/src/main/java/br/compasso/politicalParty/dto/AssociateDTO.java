package br.compasso.politicalParty.dto;

import br.compasso.politicalParty.constants.Gender;
import br.compasso.politicalParty.constants.Position;
import br.compasso.politicalParty.entities.Associate;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssociateDTO {

    private Long id;
    private String name;
    private Position role;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date birthDate;
    private Gender gender;

    public AssociateDTO(Associate associate) {
        this.id = associate.getId();
        this.name = associate.getName();
        this.role = associate.getRole();
        this.birthDate = associate.getBirthDate();
        this.gender = associate.getGender();
    }

}
