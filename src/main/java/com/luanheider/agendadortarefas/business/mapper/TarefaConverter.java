package com.luanheider.agendadortarefas.business.mapper;

import com.luanheider.agendadortarefas.business.dto.TarefaDTO;
import com.luanheider.agendadortarefas.infrastructure.entity.TarefaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaConverter {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    TarefaEntity paraTarefaEntity(TarefaDTO tarefasDTO);

    TarefaDTO paraTarefaDTO(TarefaEntity tarefasEntity);

    List<TarefaEntity> paraListaTarefasEntity(List<TarefaDTO> tarefaDTOS);

    List<TarefaDTO> paraListaTarefasDTO(List<TarefaEntity> tarefaEntities);
}
