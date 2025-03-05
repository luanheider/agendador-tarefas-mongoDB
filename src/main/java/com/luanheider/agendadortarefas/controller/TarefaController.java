package com.luanheider.agendadortarefas.controller;

import com.luanheider.agendadortarefas.business.TarefaService;
import com.luanheider.agendadortarefas.business.dto.TarefaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}