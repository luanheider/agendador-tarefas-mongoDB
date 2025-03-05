package com.luanheider.agendadortarefas.controller;

import com.luanheider.agendadortarefas.business.TarefaService;
import com.luanheider.agendadortarefas.business.dto.TarefaDTO;
import com.luanheider.agendadortarefas.infrastructure.enums.StatusNotificacaoEnum;
import com.luanheider.agendadortarefas.infrastructure.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
@RequiredArgsConstructor
public class TarefaController {
    private final TarefaService tarefaService;

    @PostMapping
    public ResponseEntity<TarefaDTO> gravarTarefa(@RequestBody TarefaDTO tarefaDTO,
                                                  @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, tarefaDTO));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefaDTO>> buscartarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal) {
        return ResponseEntity.ok(tarefaService.buscarTarefasAgendadasPorPeriodo(dataInicial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscarTarefasPorEmail(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(tarefaService.buscarTarefasPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("id") String id) {
        try {
            tarefaService.deletarTarefaPorId(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id. Id não encontrado"
                    + id, e.getCause());
        }
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefaDTO> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                             @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.alterarStatusDaTarefa(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> updateTarefa(@RequestBody TarefaDTO tarefaDTO,
                                                  @RequestParam("id") String id) {
        return ResponseEntity.ok(tarefaService.updateTarefas(tarefaDTO, id));
    }
}