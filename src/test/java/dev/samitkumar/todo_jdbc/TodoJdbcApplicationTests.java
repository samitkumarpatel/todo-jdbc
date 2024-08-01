package dev.samitkumar.todo_jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.mapping.AggregateReference;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertAll;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TodoJdbcApplicationTests {

	@Autowired
	TaskRepository taskRepository;

	@Autowired
	TagRepository tagRepository;
    @Autowired
    private TaskTagRepository taskTagRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void crudTest() {
		assertAll(
				() -> {
					//Persist tag
					var tags = tagRepository.saveAll(
							List.of(
									new Tag(null, 1L, "URGENT"),
									new Tag(null, 1L, "EDUCATION")
							)
					);

					tags.forEach(System.out::println);

					//Persist task

					var task = taskRepository.saveAll(
							List.of(
									new Task(null, 1L, "t1", "t1d", LocalDate.now(), false, Set.of(
											new TaskTag(null, null, AggregateReference.to(tags.getFirst().tagId()))
									)),
									new Task(null, 1L, "t2", "t2d", LocalDate.now(), false, Set.of(
											new TaskTag(null, null, AggregateReference.to(tags.getFirst().tagId())),
											new TaskTag(null, null, AggregateReference.to(tags.getFirst().tagId()))
									)),
									new Task(null, 1L, "t3", "t3d", LocalDate.now(), false, Set.of())
							)
					);

					task.forEach(System.out::println);

				},
				() -> {
					System.out.println("-------------FIND ALL-------------");
					tagRepository.findAll().forEach(System.out::println);
					taskRepository.findAll().forEach(System.out::println);
					taskTagRepository.findAll().forEach(System.out::println);
				}
		);
	}

}
