package br.compasso.politicalParty.services;

import br.compasso.politicalParty.constants.Position;
import br.compasso.politicalParty.dto.AssociateDTO;
import br.compasso.politicalParty.dto.AssociateFormDTO;
import br.compasso.politicalParty.dto.AssociatePartyFormDTO;
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

import java.util.Optional;

@Service
public class AssociateService {

    @Autowired
    private AssociateRepository associateRepository;

    @Autowired
    private PoliticalPartyRepository politicalPartyRepository;

    @Autowired
    private ModelMapper modelMapper;

    public AssociateDTO insert(AssociateFormDTO associateFormDTO){
        Associate associate = associateRepository.save(modelMapper.map(associateFormDTO, Associate.class));
        return modelMapper.map(associate, AssociateDTO.class);
    }

    public ResponseEntity createAssociateParty(AssociatePartyFormDTO associatePartyFormDTO) {

        Optional<Associate> associate = associateRepository.findById(associatePartyFormDTO.getAssociateId());
        Optional<PoliticalParty> politicalParty = politicalPartyRepository.findById(associatePartyFormDTO.getPoliticalPartyId());

        if(associate.isPresent() && politicalParty.isPresent()){

            associate.get().setPoliticalParty(politicalParty.get());
            associateRepository.save(associate.get());

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.noContent().build();
    }

    public Page<AssociateDTO> find(String role, Pageable pageable) {

        if(role == null){
            Page<Associate> associates = associateRepository.findAll(pageable);
            return convert(associates);
        }else {
            Page<Associate> associates = associateRepository.findByRole(Position.valueOf(role), pageable);
            return convert(associates);
        }
    }

    public ResponseEntity<AssociateDTO> findById(Long id) {

        Optional<Associate> associate = associateRepository.findById(id);

        if (associate.isPresent()){
            return ResponseEntity.ok().body(modelMapper.map(associate.get(), AssociateDTO.class));
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity<AssociateDTO> updateById(Long id, AssociateFormDTO associateFormDTO) {

        Optional<Associate> associate = associateRepository.findById(id);

        if(associate.isPresent()){
            Associate updatedAssociate = update(id, associateFormDTO);
            return ResponseEntity.ok().body(modelMapper.map(associate.get(), AssociateDTO.class));
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity<AssociateDTO> removeById(Long id) {

        Optional<Associate> associate = associateRepository.findById(id);

        if(associate.isPresent()){
            associateRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    public ResponseEntity<AssociateDTO> removeFromParty(Long associateId, Long partyId) {

        Optional<Associate> associate = associateRepository.findById(associateId);
        Optional<PoliticalParty> politicalParty = politicalPartyRepository.findById(partyId);

        if(associate.isPresent() && politicalParty.isPresent()){
            associate.get().setPoliticalParty(null);
            associateRepository.save(associate.get());

            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.noContent().build();
        }
    }

    private Associate update(Long id, AssociateFormDTO associateFormDTO) {

        Associate associate = associateRepository.getById(id);
        associate.setName(associateFormDTO.getName());
        associate.setRole(associateFormDTO.getRole());
        associate.setBirthDate(associateFormDTO.getBirthDate());
        associate.setGender(associateFormDTO.getGender());

        return associate;
    }

    private Page<AssociateDTO> convert(Page<Associate> associates) {
        return associates.map(AssociateDTO::new);
    }
}
