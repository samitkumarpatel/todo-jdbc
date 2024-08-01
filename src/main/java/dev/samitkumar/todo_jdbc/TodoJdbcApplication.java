package dev.samitkumar.todo_jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.repository.ListCrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class TodoJdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoJdbcApplication.class, args);
	}

}
@Table("tasks")
record Task(
		@Id Long taskId,
		Long userId,
		String title,
		String description,
		LocalDate dueDate,
		Boolean isCompleted,
		@MappedCollection(idColumn = "task_id", keyColumn = "tag_id")
		Set<TaskTag> tags) {}

@Table("tags")
record Tag(
		@Id Long tagId,
		Long userId,
		String tagName) {}

@Table("task_tags")
record TaskTag(
		@Id
		Long id,
		Long taskId,
		AggregateReference<Tag, Long> tagId) {}

interface TaskRepository extends ListCrudRepository<Task, Long> {}
interface TagRepository extends ListCrudRepository<Tag, Long> {}
interface TaskTagRepository extends ListCrudRepository<TaskTag, Long> {}
