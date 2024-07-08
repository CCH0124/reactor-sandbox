package com.example.firstcrud.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.firstcrud.domain.Tutorial;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@DataJpaTest(
    includeFilters =
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = TutorialRepository.class),
    showSql = false)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisabledInAotMode
@Testcontainers
public class TutorialRepositoryTest {

  @Autowired TutorialRepository repository;

  Tutorial tutorial;

  @Container @ServiceConnection
  static PostgreSQLContainer<?> postgresContainer =
      new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
          .withDatabaseName("dev")
          .withUsername("dev")
          .withPassword("123456")
          .withInitScript("init.sql");

  @DynamicPropertySource
  static void setPostgresDataSourceProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.username", postgresContainer::getUsername);
    registry.add("spring.datasource.password", postgresContainer::getPassword);
    registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
    registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
    registry.add("spring.liquibase.enabled", () -> "false");
  }

  @BeforeAll
  static void beforeAll() {
    postgresContainer.start();
  }

  @AfterAll
  static void afterAll() {
    postgresContainer.stop();
  }

  @AfterEach
  void tearDown() {
    repository.deleteAll();
  }

  @Test
  public void should_find_no_tutorials_if_repository_is_empty() {
    var tutorial = new Tutorial();
    tutorial.setTitle("title");
    tutorial.setPublished(false);
    tutorial.setDescription("description");
    tutorial.setLevel(2);

    var tutorials = repository.findAll();

    assertThat(tutorials).isEmpty();
  }

  @Test
  public void should_store_a_tutorial() {
    var tutorial = new Tutorial();
    tutorial.setTitle("title");
    tutorial.setPublished(false);
    tutorial.setDescription("description");
    tutorial.setLevel(2);
    var save = repository.save(tutorial);

    assertThat(save).hasFieldOrPropertyWithValue("title", "title");
    assertThat(save).hasFieldOrPropertyWithValue("description", "description");
    assertThat(save).hasFieldOrPropertyWithValue("published", false);
    assertThat(save).hasFieldOrPropertyWithValue("level", 2);
  }

  @Test
  public void should_find_all_tutorials() {
    var t1 = new Tutorial();
    t1.setTitle("title1");
    t1.setDescription("Desc1");
    t1.setPublished(true);
    t1.setLevel(1);
    var save1 = repository.save(t1);
    ;

    var t2 = new Tutorial();
    t2.setTitle("title2");
    t2.setDescription("Desc2");
    t2.setPublished(false);
    t2.setLevel(2);
    var save2 = repository.save(t2);

    var t3 = new Tutorial();
    t3.setTitle("title3");
    t3.setDescription("Desc3");
    t3.setPublished(false);
    t3.setLevel(3);
    var save3 = repository.save(t3);

    var tutorials = repository.findAll();

    assertThat(tutorials).hasSize(3).contains(save1, save2, save3);
  }

  @Test
  public void should_find_published_tutorials() {
    var t1 = new Tutorial();
    t1.setTitle("title1");
    t1.setDescription("Desc1");
    t1.setPublished(true);
    t1.setLevel(1);
    var save1 = repository.save(t1);
    ;

    var t2 = new Tutorial();
    t2.setTitle("title2");
    t2.setDescription("Desc2");
    t2.setPublished(false);
    t2.setLevel(2);
    repository.save(t2);

    var t3 = new Tutorial();
    t3.setTitle("title3");
    t3.setDescription("Desc3");
    t3.setPublished(false);
    t3.setLevel(3);
    repository.save(t3);

    Pageable paging = PageRequest.of(0, 3);

    var tutorials = repository.findByPublished(true, paging);
    System.out.println(tutorials.getContent());
    assertThat(tutorials.getContent()).hasSize(1).contains(save1);
  }

  @Test
  public void should_find_tutorials_by_title_containing_string() {
    var t1 = new Tutorial();
    t1.setTitle("title1");
    t1.setDescription("Desc1");
    t1.setPublished(true);
    t1.setLevel(1);
    var save1 = repository.save(t1);
    ;

    var t2 = new Tutorial();
    t2.setTitle("title2");
    t2.setDescription("Desc2");
    t2.setPublished(false);
    t2.setLevel(2);
    var save2 = repository.save(t2);

    var t3 = new Tutorial();
    t3.setTitle("java3");
    t3.setDescription("Desc3");
    t3.setPublished(false);
    t3.setLevel(3);
    var save3 = repository.save(t3);

    Pageable paging = PageRequest.of(0, 3);

    var tutorials = repository.findByTitleContaining("title", paging);

    assertThat(tutorials).hasSize(2).contains(save1, save2);
  }
}
