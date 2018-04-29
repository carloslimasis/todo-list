package br.com.todo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.todo.models.Todo;
import br.com.todo.services.TodoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TodoController {

	@Autowired
	private TodoService service;

	@ApiOperation(value = "GET All todos", response = Todo.class, notes = "This operation allows to GET all todos")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully retrieved todos", response = Todo.class),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")

	})
	@GetMapping("/todos")
	@ResponseStatus(HttpStatus.OK)
	public List<Todo> getAllTodos() {
		return service.getAllTodos();
	}

	@ApiOperation(value = "Post todo", response = Todo.class, notes = "This operation allows to create a new todo")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully created todo", response = Todo.class),
			@ApiResponse(code = 500, message = "Internal Server Error")

	})
	@PostMapping("/todos")
	@ResponseStatus(HttpStatus.CREATED)
	public Todo createTodo(@Valid @RequestBody Todo todo) {
		return service.createTodo(todo);
	}

	@ApiOperation(value = "GET todo by id", response = Todo.class, notes = "This operation allows to get a todo by id")
	@GetMapping("/todos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Todo getTodoById(@PathVariable("id") Integer id) {
		return service.getTodoById(id);
	}

	@ApiOperation(value = "PUT todo", response = Todo.class, notes = "This operation allows to update a todo")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully updated todo", response = Todo.class),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")

	})
	@PutMapping("/todos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Todo updateTodo(@PathVariable(value = "id") Integer id, @Valid @RequestBody Todo todoDetails) {
		return service.updateTodo(id, todoDetails);
	}

	@ApiOperation(value = "DELETE todo", response = Todo.class, notes = "This operation allows to delete a todo")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Successfully deleted todo"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal Server Error")

	})
	@DeleteMapping("/todos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteTodo(@PathVariable(value = "id") Integer id) {
		service.deleteTodo(id);
		return ResponseEntity.ok().build();
	}
}
