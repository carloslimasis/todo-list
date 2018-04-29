package br.com.todo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.todo.exceptions.NotFoundException;
import br.com.todo.models.Todo;
import br.com.todo.repositories.TodoRepository;
import br.com.todo.services.TodoService;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
	
	private final TodoRepository repository;

	@Autowired
	public TodoServiceImpl(TodoRepository repository) {
		this.repository = repository;
	}

	@Override
	public List<Todo> getAllTodos() {
		return repository.findAll();
	}

	@Override
	public Todo createTodo(Todo todo) {
		return repository.save(todo);
	}

	@Override
	public Todo getTodoById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new NotFoundException("Todo", "id", id));
	}

	@Override
	public Todo updateTodo(Integer id, Todo todoDetails) {
		Todo todo = getTodoById(id);

		todo.setTitle(todoDetails.getTitle());
		todo.setCompleted(todoDetails.isCompleted());

		return repository.save(todo);
	}

	@Override
	public void deleteTodo(Integer id) {
		Todo todo = getTodoById(id);
		repository.delete(todo);
	}

	
	
}
