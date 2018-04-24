package br.com.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
