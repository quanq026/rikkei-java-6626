CREATE DATABASE IF NOT EXISTS movie_db;
USE movie_db;

CREATE TABLE IF NOT EXISTS movies (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    director VARCHAR(255) NOT NULL,
    year INT NOT NULL
);

DELIMITER //

CREATE PROCEDURE add_movie(IN p_title VARCHAR(255), IN p_director VARCHAR(255), IN p_year INT)
BEGIN
    INSERT INTO movies(title, director, year) VALUES (p_title, p_director, p_year);
END //

CREATE PROCEDURE list_movies()
BEGIN
    SELECT id, title, director, year FROM movies;
END //

CREATE PROCEDURE update_movie(IN p_id INT, IN p_title VARCHAR(255), IN p_director VARCHAR(255), IN p_year INT)
BEGIN
    UPDATE movies SET title = p_title, director = p_director, year = p_year WHERE id = p_id;
END //

CREATE PROCEDURE delete_movie(IN p_id INT)
BEGIN
    DELETE FROM movies WHERE id = p_id;
END //

DELIMITER ;
