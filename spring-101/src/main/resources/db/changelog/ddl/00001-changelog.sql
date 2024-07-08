CREATE TABLE IF NOT EXISTS tutorials (
   id UUID DEFAULT uuid_generate_v4() NOT NULL,
   title VARCHAR(20) NOT NULL,
   description TEXT NOT NULL,
   published BOOLEAN DEFAULT FALSE,
   level int NOT NULL,
   created_at timestamp DEFAULT CURRENT_TIMESTAMP,
   CONSTRAINT pk_tutorials PRIMARY KEY (id)
);
