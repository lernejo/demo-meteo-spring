package fr.lernejo.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("fr.lernejo.meteo")
public class TodoListApp {
    public static void main(String[] args){
        SpringApplication.run(TodoListApp.class, args);
    }
}
