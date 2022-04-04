package br.compasso.politicalParty.repositories;

import br.compasso.politicalParty.constants.Position;
import br.compasso.politicalParty.entities.Associate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssociateRepository extends JpaRepository<Associate, Long> {

    Page<Associate> findByRole(Position role, Pageable pageable);

    List<Associate> findByPoliticalPartyId(Long id);
}
