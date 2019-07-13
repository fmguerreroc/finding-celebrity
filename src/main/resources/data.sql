
DROP TABLE IF EXISTS person;

CREATE TABLE person (
  person_id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250),
  last_name VARCHAR(250)
);

DROP TABLE IF EXISTS relationship;

CREATE TABLE relationship (
  id BIGINT AUTO_INCREMENT  PRIMARY KEY,
  owner_id BIGINT NOT NULL,
  target_id BIGINT NOT NULL
);

ALTER TABLE relationship ADD FOREIGN KEY (owner_id) REFERENCES person(person_id);
ALTER TABLE relationship ADD FOREIGN KEY (target_id) REFERENCES person(person_id);

INSERT INTO person VALUES
  (1, 'Fredy', 'Guerrero'),
  (2, 'Bill', 'Gates'),
  (3, 'Folrunsho', 'Alakija');

INSERT INTO relationship VALUES
  (1, 2, 1),
  (2, 3, 1);