package com.cauasturaro.todolist.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record Tarefa (
    Long id,

    @NotBlank(message = "A descrição não pode estar vazia")
    @Size(max = 255, message = "A descrição não pode exceder 255 aaracteres.")
    String descricao,

    boolean concluida
){

    public Tarefa withDescricao(String descricao){
        return new Tarefa(id, descricao, concluida);
    }

    public Tarefa withConcluida(boolean concluida){
        return new Tarefa(id, descricao, concluida);
    }
}
