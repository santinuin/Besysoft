/* Populate tabla generos */

INSERT INTO generos (id, nombre) VALUES (1L, 'Comedia');
INSERT INTO generos (id, nombre) VALUES (2L, 'Terror');
INSERT INTO generos (id, nombre) VALUES (3L, 'Drama');
INSERT INTO generos (id, nombre) VALUES (4L, 'Acción');

/* Populate tabla peliculas */

INSERT INTO peliculas (id, titulo, fecha_de_creacion, calificacion, genero_id) VALUES (1L, 'Voley', '2015-03-12', 4, 1L);
INSERT INTO peliculas (id, titulo, fecha_de_creacion, calificacion, genero_id) VALUES (2L, 'Un novio para mi mujer', '2008-06-14', 4, 1L);
INSERT INTO peliculas (id, titulo, fecha_de_creacion, calificacion, genero_id) VALUES (3L, 'Esperando la carroza', '1985-05-06', 5, 1L);
INSERT INTO peliculas (id, titulo, fecha_de_creacion, calificacion, genero_id) VALUES (4L, 'El grito', '2004-10-22', 4, 2L);
INSERT INTO peliculas (id, titulo, fecha_de_creacion, calificacion, genero_id) VALUES (5L, 'La llave maestra', '2005-08-12', 3, 2L) ;
INSERT INTO peliculas (id, titulo, fecha_de_creacion, calificacion, genero_id) VALUES (6L, 'La casa de cera', '2005-04-26', 2, 2L);
INSERT INTO peliculas (id, titulo, fecha_de_creacion, calificacion, genero_id) VALUES (7L, 'Historia de un matrimonio', '2019-11-15', 3, 3L);
INSERT INTO peliculas (id, titulo, fecha_de_creacion, calificacion, genero_id) VALUES (8L, 'El niño que domo el viento', '2019-01-25', 4, 3L);
INSERT INTO peliculas (id, titulo, fecha_de_creacion, calificacion, genero_id) VALUES (9L, 'Birdman', '2014-10-17', 5, 3L);
INSERT INTO peliculas (id, titulo, fecha_de_creacion, calificacion, genero_id) VALUES (10L, 'Avengers', '2012-04-11', 4, 4L);
INSERT INTO peliculas (id, titulo, fecha_de_creacion, calificacion, genero_id) VALUES (11L, 'Django', '2012-01-18', 5, 4L);

/* Populate tabla personajes */

INSERT INTO personajes (id, nombre, edad, peso, historia, pelicula_id) VALUES (1L, 'Pilar', 27, 49, 'Chica sensible, extrovertida, perceptiva que cree en lo magico y lo maravilloso', 1L);
INSERT INTO personajes (id, nombre, edad, peso, historia, pelicula_id) VALUES (2L, 'Tana', 34, 62, 'Persona constantemente malhumorada: si no protesta por el tiempo es por el gobierno; si no es por los vecinos, jovenes o jubilados', 2L);
INSERT INTO personajes (id, nombre, edad, peso, historia, pelicula_id) VALUES (3L, 'Cora', 78, 70, 'Viuda, madre de cuatro hijos, vive en el barrio Versalles con su hijo Jorge y su nuera Susana, a quienes provoca varias incomodidades', 3L);
INSERT INTO personajes (id, nombre, edad, peso, historia, pelicula_id) VALUES (4L, 'Kayako', 27, 45, 'Joven japonesa infelizmente casada, obsesionada con un profesor estadounidense que trabaja en japon', 4L);
INSERT INTO personajes (id, nombre, edad, peso, historia, pelicula_id) VALUES (5L, 'Caroline', 32, 56, 'Chica de Nueva Orleans que ocupa una posicion como proveedor de cuidados de hospicio privado', 5L);
INSERT INTO personajes (id, nombre, edad, peso, historia, pelicula_id) VALUES (6L, 'Carly', 30, 58, 'Joven amante de los animales, perdidamente enamorada de su novio Wade, aventurera e inquieta', 6L);
INSERT INTO personajes (id, nombre, edad, peso, historia, pelicula_id) VALUES (7L, 'Charly', 45, 82, 'Existoso director de teatro de la ciudad de Nueva York', 7L);
INSERT INTO personajes (id, nombre, edad, peso, historia, pelicula_id) VALUES (8L, 'William', 10, 32, 'Es un joven escolar que proviene de una familia de granjeros', 8L);
INSERT INTO personajes (id, nombre, edad, peso, historia, pelicula_id) VALUES (9L, 'Riggan', 54, 85, 'Decadente actor de Hollywood famoso por su papel de superheroe Birdman en exitosas peliculas de decadas pasadas', 9L);
INSERT INTO personajes (id, nombre, edad, peso, historia, pelicula_id) VALUES (10L, 'Tony', 52, 78, 'Fabricante, genio inventor, heroe, y ex playboy, propietario de industrias Stark', 10L);
INSERT INTO personajes (id, nombre, edad, peso, historia, pelicula_id) VALUES (11L, 'Django', 36, 80, 'Esclavo negro vendido a los hermanos Espec', 11L);