package com.example.pokemon.mappers;

import com.example.pokemon.dto.BerryDto;
import com.example.pokemon.entities.Berry;
import org.mapstruct.Mapper;

@Mapper(uses = {StringToListMapper.class})
public interface BerryMappers {
    Berry berryDtoToBerry(BerryDto berryDto);
    BerryDto berryToBerryDto(Berry berry);
}
