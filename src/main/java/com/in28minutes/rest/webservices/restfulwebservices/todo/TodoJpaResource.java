package com.in28minutes.rest.webservices.restfulwebservices.todo;

import com.in28minutes.rest.webservices.restfulwebservices.todo.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class TodoJpaResource {

    private TodoService todoService;

    private TodoRepository todoRepository;

    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username) {
        return todoRepository.findByUsername(username);
//        return todoService.findByUsername(username);
    }

    @GetMapping("/users/{username}/todo/{id}")
    public Todo retrieveTodo(@PathVariable String username, @PathVariable int id) {
        return todoRepository.findById(id).get();
//        return todoService.findById(id);
    }

    @DeleteMapping("/users/{username}/todo/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username, @PathVariable int id) {
        todoRepository.deleteById(id);
//        todoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todo/{id}")
    public Todo updateTodo(@PathVariable String username, @PathVariable int id, @RequestBody Todo todo) {
        todo.setUsername(username);
        todo.setId(null);
        todoRepository.save(todo);
//        todoService.updateTodo(todo);
        return todo;
    }

    @PostMapping("/users/{username}/todo")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo) {
        Todo createTodo = todoRepository.save(todo);
//        Todo createTodo = todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone());
        return createTodo;
    }
}
