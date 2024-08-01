package dev.samitkumar.todo_jdbc;

import org.springframework.boot.SpringApplication;

public class TestTodoJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.from(TodoJdbcApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
