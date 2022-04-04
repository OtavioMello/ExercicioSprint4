package br.compasso.politicalParty.controllers;

import br.compasso.politicalParty.dto.AssociateDTO;
import br.compasso.politicalParty.dto.PoliticalPartyDTO;
import br.compasso.politicalParty.dto.PoliticalPartyFormDTO;
import br.compasso.politicalParty.services.PoliticalPartyService;
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
import java.util.List;

@RestController
@RequestMapping("/partidos")
public class PoliticalPartyController {

    @Autowired
    private PoliticalPartyService politicalPartyService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @Transactional
    public ResponseEntity<PoliticalPartyDTO> create
            (@RequestBody @Valid PoliticalPartyFormDTO politicalPartyFormDTO,
             UriComponentsBuilder uriComponentsBuilder){

        PoliticalPartyDTO politicalParty = politicalPartyService.insert(politicalPartyFormDTO);

        URI uri = uriComponentsBuilder.path("/partidos/{id}").buildAndExpand(politicalParty.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public Page<PoliticalPartyDTO> find
            (@RequestParam(required = false) String ideology,
             @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable){

        Page<PoliticalPartyDTO> politicalParty = politicalPartyService.find(ideology, pageable);

        return politicalParty;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PoliticalPartyDTO> findById(@PathVariable Long id){

        ResponseEntity<PoliticalPartyDTO> politicalParty = politicalPartyService.findById(id);

        return politicalParty;
    }

    @GetMapping("/{id}/associados")
    public ResponseEntity<List<AssociateDTO>> findAssociates(@PathVariable Long id){

        List<AssociateDTO> associate = politicalPartyService.findAssociates(id);

        if (associate.isEmpty()){
            return ResponseEntity.noContent().build();
        }else {
            return ResponseEntity.ok().body(associate);
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PoliticalPartyDTO> update
            (@PathVariable Long id, @RequestBody @Valid PoliticalPartyFormDTO politicalPartyFormDTO){

        ResponseEntity<PoliticalPartyDTO> politicalParty =
                politicalPartyService.updateById(id, politicalPartyFormDTO);

        return politicalParty;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id){

        ResponseEntity<PoliticalPartyDTO> politicalParty = politicalPartyService.removeById(id);

        return politicalParty;
    }
}
