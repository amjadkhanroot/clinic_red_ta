INSERT INTO roles (name) VALUES ("ROLE_DOCTOR"),("ROLE_PATIENT");

INSERT INTO users (active, email, password, username) VALUES (1,"amjad@doctor.com", "akhandoctor"),(1,"amjad@patient.com", "akhanpatient");

INSERT INTO users_roles (user_id, roles_id) VALUES (1,1),(2,2);