package com.luanheider.agendadortarefas.business.mapper;

import com.luanheider.agendadortarefas.business.dto.TarefaDTO;
import com.luanheider.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {
    void updateTarefas(TarefaDTO tarefaDTO, @MappingTarget TarefaEntity tarefaEntity);
}
