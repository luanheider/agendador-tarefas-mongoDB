package com.luanheider.agendadortarefas.business.mapper;

import com.luanheider.agendadortarefas.business.dto.TarefaDTO;
import com.luanheider.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefaConverter {
    TarefaEntity paraTarefaEntity(TarefaDTO tarefasDTO);
    TarefaDTO paraTarefaDTO(TarefaEntity tarefasEntity);
}
