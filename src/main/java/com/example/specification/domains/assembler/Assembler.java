package com.example.specification.domains.assembler;

import com.example.specification.domains.dto.AbstractDTO;

import java.util.Collection;
import java.util.List;

public interface Assembler<DTO extends AbstractDTO, Entity> {
    DTO assembleDTO(Entity entity);

    Entity assembleEntity(DTO dto);

    Entity assembleEntity(DTO dto, Entity entity);

    List<DTO> assembleCollectionDTO(Collection<Entity> entities);

    List<Entity> assembleCollection(Collection<DTO> dtos);
}