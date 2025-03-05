package com.luanheider.agendadortarefas.business;

import com.luanheider.agendadortarefas.business.dto.TarefaDTO;
import com.luanheider.agendadortarefas.business.mapper.TarefaConverter;
import com.luanheider.agendadortarefas.infrastructure.entity.TarefaEntity;
import com.luanheider.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.luanheider.agendadortarefas.infrastructure.repository.TarefaRepository;
import com.luanheider.agendadortarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final TarefaConverter tarefaConverter;
    private final JwtUtil jwtUtil;

    public TarefaDTO gravarTarefa(String token, TarefaDTO tarefaDTO) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        tarefaDTO.setEmailUsuario(email);
        tarefaDTO.setDataCriacao(LocalDateTime.now());
        tarefaDTO.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        TarefaEntity tarefaEntity = tarefaConverter.paraTarefaEntity(tarefaDTO);
        return tarefaConverter.paraTarefaDTO(tarefaRepository.save(tarefaEntity));
    }
}