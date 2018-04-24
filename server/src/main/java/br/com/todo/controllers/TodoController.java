package br.com.todo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.todo.exception.ResourceNotFoundException;
import br.com.todo.model.Todo;
import br.com.todo.repository.TodoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoController {

	@Autowired
	private TodoRepository repository;

	@ApiOperation(value = "GET All todos", response = Todo.class, notes = "This operation allows to GET all todos.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Returns all todos", response = Todo.class),
			@ApiResponse(code = 500, message = "Internal Server Error", response = Todo.class)

	})
	@GetMapping("/todos")
	public List<Todo> getAllTodos() {
		return repository.findAll();
	}

	@PostMapping("/todos")
	public Todo createTodo(@Valid @RequestBody Todo todo) {
		return repository.save(todo);
	}

	@GetMapping("/todos/{id}")
	public Todo getTodoById(@PathVariable("id") Integer id) {
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo", "id", id));
	}

	@PutMapping("/todos/{id}")
	public Todo updateTodo(@PathVariable(value = "id") Integer id, @Valid @RequestBody Todo todoDetails) {
		Todo todo = getTodoById(id);

		todo.setTitle(todoDetails.getTitle());
		todo.setComplete(todoDetails.isComplete());

		return repository.save(todo);
	}

	@DeleteMapping("/todos/{id}")
	public ResponseEntity<?> deleteTodo(@PathVariable(value = "id") Integer id) {
		Todo todo = getTodoById(id);

		repository.delete(todo);

		return ResponseEntity.ok().build();
	}
}
