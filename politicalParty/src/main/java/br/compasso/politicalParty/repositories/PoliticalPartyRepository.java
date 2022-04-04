package br.compasso.politicalParty.repositories;

import br.compasso.politicalParty.constants.Ideology;
import br.compasso.politicalParty.entities.PoliticalParty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliticalPartyRepository extends JpaRepository<PoliticalParty, Long> {

    Page<PoliticalParty> findByIdeology(Ideology ideology, Pageable pageable);

}
