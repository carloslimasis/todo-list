package br.com.todo.services;

import static java.util.Arrays.asList;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import br.com.todo.exceptions.NotFoundException;
import br.com.todo.models.Todo;
import br.com.todo.repositories.TodoRepository;
import br.com.todo.services.impl.TodoServiceImpl;

public class TodoServiceTest {

	private TodoService service;
    private TodoRepository repository;
    private Todo todo;

    @Before
    public void setUp() {
    	this.repository = Mockito.mock(TodoRepository.class);
    	this.service = new TodoServiceImpl(repository);
    	this.todo = new Todo();
    	this.todo.setId(1);
    	this.todo.setTitle("New Task");
    	this.todo.setCreatedAt(new Date());
    }

    @Test
    public void createTodoSuccessfuly() throws Exception {
    	when(this.repository.save(this.todo)).thenReturn(this.todo);
        Todo todo = this.service.createTodo(this.todo);
        assertEquals("New Task", todo.getTitle());
        assertNotNull(todo.getCreatedAt());
    }
    
    @Test
    public void findAllSuccessfuly() throws Exception {
    	when(this.repository.findAll()).thenReturn(asList(this.todo));
    	List<Todo> todos = service.getAllTodos();
        assertThat(todos).contains(this.todo);
    }
    
    @Test
    public void getTodoByIdSuccessfuly() throws Exception {
    	when(this.repository.findById(this.todo.getId())).thenReturn(Optional.of(this.todo));
    	Todo todoFromRepository = service.getTodoById(this.todo.getId());
    	assertEquals(todoFromRepository.getTitle(), this.todo.getTitle());
    }
    
    @Test(expected = NotFoundException.class)
    public void getTodoByIdNotFoundException() throws Exception {
    	Todo todoFromRepository = service.getTodoById(this.todo.getId());
    	assertEquals(todoFromRepository.getTitle(), this.todo.getTitle());
    }
    
    @Test
    public void deleteTodoSuccessfuly() throws Exception {
    	when(this.repository.findById(this.todo.getId())).thenReturn(of(this.todo));
    	when(this.repository.findAll()).thenReturn(emptyList());
    	doNothing().when(this.repository).delete(Mockito.any());
    	service.deleteTodo(this.todo.getId());
    	List<Todo> todos = service.getAllTodos();
    	assertThat(todos).isEmpty();
    }
}
