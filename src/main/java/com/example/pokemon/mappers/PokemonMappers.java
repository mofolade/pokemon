package com.example.pokemon.mappers;

import com.example.pokemon.dto.PokemonDto;
import com.example.pokemon.entities.Pokemon;
import org.mapstruct.Mapper;

@Mapper(uses = {StringToListMapper.class})
public interface PokemonMappers {
    Pokemon pokemonDtoToMovie(PokemonDto movieDto);
    PokemonDto pokemonToMovieDto(Pokemon pokemon);
}
