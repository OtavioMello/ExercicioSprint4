package br.compasso.politicalParty.controllers;

import br.compasso.politicalParty.dto.AssociateDTO;
import br.compasso.politicalParty.dto.AssociateFormDTO;
import br.compasso.politicalParty.dto.AssociatePartyFormDTO;
import br.compasso.politicalParty.services.AssociateService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/associados")
public class AssociateController {

    @Autowired
    private AssociateService associateService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @Transactional
    public ResponseEntity<AssociateDTO> create
            (@RequestBody @Valid AssociateFormDTO associateFormDTO, UriComponentsBuilder uriComponentsBuilder){

        AssociateDTO associate = associateService.insert(associateFormDTO);

        URI uri = uriComponentsBuilder.path("/associados/{id}").buildAndExpand(associate.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/partidos")
    @Transactional
    public ResponseEntity<?> createAssociateParty
            (@RequestBody @Valid AssociatePartyFormDTO associatePartyFormDTO){

        ResponseEntity associateParty = associateService.createAssociateParty(associatePartyFormDTO);

        return associateParty;
    }

    @GetMapping
    public Page<AssociateDTO> find
            (@RequestParam(required = false) String role,
             @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){

        Page<AssociateDTO> associate = associateService.find(role, pageable);

        return associate;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociateDTO> findById(@PathVariable Long id){

        ResponseEntity<AssociateDTO> associate = associateService.findById(id);

        return associate;
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<AssociateDTO> update
            (@PathVariable Long id, @RequestBody @Valid AssociateFormDTO associateFormDTO){

        ResponseEntity<AssociateDTO> associate = associateService.updateById(id, associateFormDTO);

        return associate;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id){

        ResponseEntity<AssociateDTO> associate = associateService.removeById(id);
        return associate;
    }

    @DeleteMapping("/{associateId}/partidos/{partyId}")
    @Transactional
    public ResponseEntity<?> removeFromParty(@PathVariable Long associateId, @PathVariable Long partyId){

        ResponseEntity<AssociateDTO> associate = associateService.removeFromParty(associateId, partyId);
        return associate;
    }
}
