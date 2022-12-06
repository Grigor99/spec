package com.example.specification.domains.assembler;

import com.example.specification.domains.dto.AbstractDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractAssembler<DTO extends AbstractDTO, Domain> implements Assembler<DTO, Domain> {


    @Override
    public List<DTO> assembleCollectionDTO(Collection<Domain> domains) {
        return domains.stream().map(this::assembleDTO).collect(Collectors.toList());
    }

    @Override
    public List<Domain> assembleCollection(Collection<DTO> dtos) {
        List<Domain> list = new ArrayList<>();
        for (DTO dto : dtos) {
            Domain domain = assembleEntity(dto);
            list.add(domain);
        }
        return list;
    }
}
