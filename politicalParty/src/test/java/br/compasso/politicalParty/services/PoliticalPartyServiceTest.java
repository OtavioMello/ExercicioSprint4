package br.compasso.politicalParty.services;

import br.compasso.politicalParty.constants.Ideology;
import br.compasso.politicalParty.entities.PoliticalParty;
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

import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PoliticalPartyService.class)
public class PoliticalPartyServiceTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private PoliticalPartyRepository politicalPartyRepository;

    @MockBean
    AssociateRepository associateRepository;

    @Test
    void shouldCreateANewPoliticalParty() throws Exception {

        PoliticalParty politicalParty = PoliticalParty.builder()
                .id(1l)
                .name("Test")
                .acronym("ABC")
                .ideology(Ideology.CENTRO)
                .foundationDate(new Date(2002,04,19)).build();

        Mockito.when(politicalPartyRepository.save(politicalParty)).thenReturn(politicalParty);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/partidos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(politicalParty));

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    void shoudFindPoliticalPartyById() throws Exception {

        PoliticalParty politicalParty = PoliticalParty.builder()
                .id(1l)
                .name("Test")
                .acronym("ABC")
                .ideology(Ideology.CENTRO)
                .foundationDate(new Date(2002,04,19)).build();

        Mockito.when(politicalPartyRepository.findById(politicalParty.getId())).thenReturn(Optional.of(politicalParty));

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.get("/partidos")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.mapper.writeValueAsString(politicalParty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/partidos/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Test")));
    }
}
