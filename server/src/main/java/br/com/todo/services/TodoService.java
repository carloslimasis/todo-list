package br.com.todo.services;

import java.util.List;

import br.com.todo.models.Todo;

public interface TodoService {

	public List<Todo> getAllTodos();
	public Todo createTodo(Todo todo);
	public Todo getTodoById(Integer id);
	public Todo updateTodo(Integer id,Todo todoDetails);
	public void deleteTodo(Integer id);
	
}
