package br.compasso.politicalParty.services;

import br.compasso.politicalParty.constants.Gender;
import br.compasso.politicalParty.constants.Position;
import br.compasso.politicalParty.entities.Associate;
import br.compasso.politicalParty.repositories.AssociateRepository;
import br.compasso.politicalParty.repositories.PoliticalPartyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Date;
import java.util.Optional;


    /*
    *
    *  ATENÇÃO
    * Tentei fazer esses testes com base nesse tutorial:
    * https://stackabuse.com/guide-to-unit-testing-spring-boot-rest-apis/
    *
    * Porém, os testes não estão funcionando, não sei onde foi o meu erro, e meu tempo está acabando.
    * Portanto, vou ficar devendo os testes, infelizmente :(
    *
    * */

@WebMvcTest(AssociateService.class)
public class AssociateServiceTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private AssociateRepository associateRepository;

    @MockBean
    private PoliticalPartyRepository politicalPartyRepository;

    @Test
    void shouldCreateANewAssociate() throws Exception {

        Associate associate = Associate.builder()
                .id(1l)
                .name("Test")
                .role(Position.DEPUTADO_ESTADUAL).birthDate(new Date(2002,04,19))
                .gender(Gender.MASCULINO).build();

        Mockito.when(associateRepository.save(associate)).thenReturn(associate);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/associados")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(associate));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Test")));
    }

    @Test
    void shouldFindAssociateById() throws Exception {

        Associate associate = Associate.builder()
                .id(1l)
                .name("Test")
                .role(Position.DEPUTADO_ESTADUAL).birthDate(new Date(2002,04,19))
                .gender(Gender.MASCULINO).build();

        associateRepository.save(associate);

        Mockito.when(associateRepository.findById(associate.getId())).thenReturn(Optional.of(associate));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/associados")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(associate));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/associados/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Test")));
    }
}
