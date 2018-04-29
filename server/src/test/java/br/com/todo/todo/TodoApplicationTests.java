package br.com.todo.todo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.todo.TodoApplication;
import br.com.todo.models.Todo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TodoApplication.class, webEnvironment = RANDOM_PORT)
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class TodoApplicationTests {

    private Todo todo;

    @Before
    public void setUp() {
    	this.todo = new Todo();
    	this.todo.setTitle("New Task");
    }
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void createTodo() {
        ResponseEntity<Todo> responseEntity = restTemplate.postForEntity("/api/todos", this.todo, Todo.class);
        this.todo = responseEntity.getBody();
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("New Task", this.todo.getTitle());
        assertNotNull(this.todo.getId());
    }
    
    @Test
    public void getTodoById() {
    	this.todo.setTitle("New Task II");
    	Todo createdTodo = restTemplate.postForEntity("/api/todos", this.todo, Todo.class).getBody();
        
    	ResponseEntity<Todo> responseEntity = restTemplate.getForEntity("/api/todos/" + createdTodo.getId(), Todo.class);
        this.todo = responseEntity.getBody();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("New Task II", this.todo.getTitle());
        assertNotNull(this.todo.getId());
    }
    
    @Test
    public void getTodoByIdWithException() {
    	ResponseEntity<Todo> responseEntity = restTemplate.getForEntity("/api/todos/1", Todo.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
    
    @Test
    public void updateTodo() {
    	this.todo.setTitle("New Task III");
    	Todo createdTodo = restTemplate.postForEntity("/api/todos", this.todo, Todo.class).getBody();
    	
    	Todo todoToUpdate = createdTodo;
    	todoToUpdate.setTitle("New Task III edited");
    	
    	restTemplate.put("/api/todos/" + todoToUpdate.getId(), todoToUpdate);
        
    	ResponseEntity<Todo> responseEntity = restTemplate.getForEntity("/api/todos/" + todoToUpdate.getId(), Todo.class);
        this.todo = responseEntity.getBody();
    	
        assertEquals("New Task III edited", this.todo.getTitle());
        assertNotNull(this.todo.getId());
    }
    

    @Test
    public void deleteTodo() {
    	Todo firstTodo = restTemplate.postForEntity("/api/todos", this.todo, Todo.class).getBody();
    	restTemplate.postForEntity("/api/todos", this.todo, Todo.class).getBody();
    	
    	restTemplate.delete("/api/todos/" + firstTodo.getId(), firstTodo);
    	
    	ResponseEntity<Todo[]> responseEntity = restTemplate.getForEntity("/api/todos", Todo[].class);
    	Todo[] todos = responseEntity.getBody();
    	
    	assertThat(todos).doesNotContain(firstTodo);
    }
}
