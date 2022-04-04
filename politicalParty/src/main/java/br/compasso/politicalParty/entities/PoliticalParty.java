package br.compasso.politicalParty.entities;

import br.compasso.politicalParty.constants.Ideology;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class PoliticalParty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String acronym;
    @Enumerated(EnumType.STRING)
    private Ideology ideology;
    private Date foundationDate;
    @OneToMany
    private List<Associate> associates = new ArrayList<>();

}
