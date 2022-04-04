package br.compasso.politicalParty.services;

import br.compasso.politicalParty.constants.Ideology;
import br.compasso.politicalParty.dto.AssociateDTO;
import br.compasso.politicalParty.dto.PoliticalPartyDTO;
import br.compasso.politicalParty.dto.PoliticalPartyFormDTO;
import br.compasso.politicalParty.entities.Associate;
import br.compasso.politicalParty.entities.PoliticalParty;
import br.compasso.politicalParty.repositories.AssociateRepository;
import br.compasso.politicalParty.repositories.PoliticalPartyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PoliticalPartyService {

    @Autowired
    private PoliticalPartyRepository politicalPartyRepository;

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PoliticalPartyDTO insert(PoliticalPartyFormDTO politicalPartyFormDTO) {

        PoliticalParty politicalParty = politicalPartyRepository.save
                (modelMapper.map(politicalPartyFormDTO, PoliticalParty.class));

        return modelMapper.map(politicalParty, PoliticalPartyDTO.class);

    }

    public Page<PoliticalPartyDTO> find(String ideology, Pageable pageable) {

        if(ideology == null){
            Page<PoliticalParty> politicalParty = politicalPartyRepository.findAll(pageable);
            return converterPoliticalParties(politicalParty);
        }else {
            Page<PoliticalParty> politicalParty =
                    politicalPartyRepository.findByIdeology(Ideology.valueOf(ideology), pageable);
            return converterPoliticalParties(politicalParty);
        }
    }

    public ResponseEntity<PoliticalPartyDTO> findById(Long id) {

        Optional<PoliticalParty> politicalParty = politicalPartyRepository.findById(id);

        if(politicalParty.isPresent()){
            return ResponseEntity.ok().body(modelMapper.map(politicalParty.get(), PoliticalPartyDTO.class));
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    public List<AssociateDTO> findAssociates(Long id) {

        List<Associate> associate = associateRepository.findByPoliticalPartyId(id);

        List<AssociateDTO> associateDTO = associate.stream().map(item ->
            modelMapper.map(item, AssociateDTO.class)).collect(Collectors.toList());

        return associateDTO;
    }

    public ResponseEntity<PoliticalPartyDTO> updateById(Long id, PoliticalPartyFormDTO politicalPartyFormDTO) {

        Optional<PoliticalParty> politicalParty = politicalPartyRepository.findById(id);

        if (politicalParty.isPresent()){
            PoliticalParty updatedPoliticalParty = update(id, politicalPartyFormDTO);
            return ResponseEntity.ok().body(modelMapper.map(updatedPoliticalParty, PoliticalPartyDTO.class));
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity<PoliticalPartyDTO> removeById(Long id) {

        Optional<PoliticalParty> optionalPoliticalParty = politicalPartyRepository.findById(id);

        if(optionalPoliticalParty.isPresent()){
            politicalPartyRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    private PoliticalParty update(Long id, PoliticalPartyFormDTO politicalPartyFormDTO) {

        PoliticalParty politicalParty = politicalPartyRepository.getById(id);
        politicalParty.setName(politicalPartyFormDTO.getName());
        politicalParty.setAcronym(politicalPartyFormDTO.getAcronym());
        politicalParty.setIdeology(politicalPartyFormDTO.getIdeology());
        politicalParty.setFoundationDate(politicalPartyFormDTO.getFoundationDate());

        return politicalParty;
    }

    private Page<PoliticalPartyDTO> converterPoliticalParties(Page<PoliticalParty> politicalParties) {
        return politicalParties.map(PoliticalPartyDTO::new);
    }

}
