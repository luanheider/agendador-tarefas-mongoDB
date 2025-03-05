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
import java.util.List;

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

    public List<TarefaDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefaConverter.paraListaTarefasDTO(
                tarefaRepository.findByDataEventoBetween(dataInicial, dataFinal)
        );
    }

    public List<TarefaDTO> buscarTarefasPorEmail(String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        List<TarefaEntity> tarefaEntity = tarefaRepository.findByEmailUsuario(email);
        return tarefaConverter.paraListaTarefasDTO(tarefaEntity);
    }
}