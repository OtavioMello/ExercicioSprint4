package br.compasso.politicalParty.entities;

import br.compasso.politicalParty.constants.Gender;
import br.compasso.politicalParty.constants.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Associate {

    @Autowired
    private ModelMapper modelMapper;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Position role;
    private Date birthDate;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @ManyToOne
    @JoinColumn(name = "POLITICALPARTY_ID", referencedColumnName = "ID")
    private PoliticalParty politicalParty = null;

}

