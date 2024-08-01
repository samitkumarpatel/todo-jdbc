-- Table for Users
CREATE TABLE Users (
   user_id SERIAL PRIMARY KEY,
   username VARCHAR(100) NOT NULL,
   email VARCHAR(100) NOT NULL UNIQUE
);

INSERT INTO Users (username, email) VALUES ('one', 'one@example.com');
INSERT INTO Users (username, email) VALUES ('two', 'two@example.com');

-- Table for Tasks
CREATE TABLE Tasks (
   task_id SERIAL PRIMARY KEY,
   user_id INT NOT NULL,
   title VARCHAR(255) NOT NULL,
   description TEXT,
   due_date DATE,
   is_completed BOOLEAN DEFAULT FALSE,
   CONSTRAINT fk_user
       FOREIGN KEY (user_id)
           REFERENCES Users(user_id)
);

-- Table for Tags
CREATE TABLE Tags (
  tag_id SERIAL PRIMARY KEY,
  user_id INT NOT NULL,
  tag_name VARCHAR(100) NOT NULL,
  CONSTRAINT fk_user
      FOREIGN KEY (user_id)
          REFERENCES Users(user_id)
);

-- Join Table for Many-to-Many relationship between Tasks and Tags
CREATE TABLE Task_Tags (
   id SERIAL PRIMARY KEY,
   task_id INT NOT NULL,
   tag_id INT NOT NULL,
   CONSTRAINT fk_task
       FOREIGN KEY (task_id)
           REFERENCES Tasks(task_id),
   CONSTRAINT fk_tag
       FOREIGN KEY (tag_id)
           REFERENCES Tags(tag_id)
);
