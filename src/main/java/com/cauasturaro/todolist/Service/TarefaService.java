package com.cauasturaro.todolist.Service;

import com.cauasturaro.todolist.model.Tarefa;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class TarefaService {

    private final List<Tarefa> tarefas = new ArrayList<>();
    private Long proximoId = 0L;

    public Tarefa criar(Tarefa tarefa) {
        Tarefa novaTarefa = new Tarefa(proximoId++, tarefa.descricao(), tarefa.concluida());
        tarefas.add(novaTarefa);
        return novaTarefa;
    }

    public List<Tarefa> listar() {
        return tarefas;
    }

    public ResponseEntity<Tarefa> buscarPorId(Long id) {
        return tarefas.stream().filter(tarefa -> tarefa.id().equals(id))
                .findFirst().map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Tarefa> atualizar(Long id, Tarefa tarefaAtualizada) {
        for (int i = 0; i < tarefas.size(); i++) {
            if (tarefas.get(i).id().equals(id)) {
                Tarefa tarefaNova = tarefas.get(i).withDescricao(tarefaAtualizada.descricao()).withConcluida(tarefaAtualizada.concluida());

                tarefas.set(i, tarefaNova);
                return ResponseEntity.ok(tarefaNova);
            }
        }
        return ResponseEntity.notFound().build();
    }

    public ResponseEntity deletar(Long id) {
        if (!tarefas.removeIf(tarefa -> tarefa.id().equals(id))) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
