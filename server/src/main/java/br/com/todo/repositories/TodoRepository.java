package br.com.todo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.todo.models.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

}
