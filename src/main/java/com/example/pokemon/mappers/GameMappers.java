package com.example.pokemon.mappers;

import com.example.pokemon.dto.GameDto;
import com.example.pokemon.entities.Game;
import org.mapstruct.Mapper;

@Mapper(uses = {StringToListMapper.class})
public interface GameMappers {
    Game gameDtoToGame(GameDto gameDto);
    GameDto gameToGameDto(Game game);
}
