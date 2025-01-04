package com.stocks.stocksexchange.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stocks.stocksexchange.stockexchangeDTOs.PortfolioDTO;
import com.stocks.stocksexchange.stockexchangeEntities.Portfolio;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        
        modelMapper.getConfiguration()
            .setPropertyCondition(context -> context.getSource() != null)
            .setSkipNullEnabled(true)
            .setMatchingStrategy(MatchingStrategies.STRICT);

        // Custom mapping for Portfolio -> PortfolioDTO
        modelMapper.createTypeMap(Portfolio.class, PortfolioDTO.class)
            .addMappings(mapper -> 
                mapper.map(src -> src.getAccount().getAccountId(),
                    PortfolioDTO::setAccountId));

        return modelMapper;
    }
}