package br.compasso.politicalParty;

import br.compasso.politicalParty.entities.Associate;
import br.compasso.politicalParty.entities.PoliticalParty;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PoliticalPartyApplication {

	@Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(PoliticalPartyApplication.class, args);
	}

}
