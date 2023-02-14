/* Populate tabla generos */

INSERT INTO generos (nombre) VALUES ('Comedia');
INSERT INTO generos (nombre) VALUES ('Terror');
INSERT INTO generos (nombre) VALUES ('Drama');
INSERT INTO generos (nombre) VALUES ('Acción');

/* Populate tabla peliculas */

INSERT INTO peliculas (titulo, fecha_de_creacion, calificacion, genero_id) VALUES ('Voley', '2015-03-12', 4, 1);
INSERT INTO peliculas (titulo, fecha_de_creacion, calificacion, genero_id) VALUES ('Un novio para mi mujer', '2008-06-14', 4, 1);
INSERT INTO peliculas (titulo, fecha_de_creacion, calificacion, genero_id) VALUES ('Esperando la carroza', '1985-05-06', 5, 1);
INSERT INTO peliculas (titulo, fecha_de_creacion, calificacion, genero_id) VALUES ('El grito', '2004-10-22', 4, 2);
INSERT INTO peliculas (titulo, fecha_de_creacion, calificacion, genero_id) VALUES ('La llave maestra', '2005-08-12', 3, 2) ;
INSERT INTO peliculas (titulo, fecha_de_creacion, calificacion, genero_id) VALUES ('La casa de cera', '2005-04-26', 2, 2);
INSERT INTO peliculas (titulo, fecha_de_creacion, calificacion, genero_id) VALUES ('Historia de un matrimonio', '2019-11-15', 3, 3);
INSERT INTO peliculas (titulo, fecha_de_creacion, calificacion, genero_id) VALUES ('El niño que domo el viento', '2019-01-25', 4, 3);
INSERT INTO peliculas (titulo, fecha_de_creacion, calificacion, genero_id) VALUES ('Birdman', '2014-10-17', 5, 3);
INSERT INTO peliculas (titulo, fecha_de_creacion, calificacion, genero_id) VALUES ('Avengers', '2012-04-11', 4, 4);
INSERT INTO peliculas (titulo, fecha_de_creacion, calificacion, genero_id) VALUES ('Django', '2012-01-18', 5, 4);

/* Populate tabla personajes */

INSERT INTO personajes (nombre, edad, peso, historia, pelicula_id) VALUES ('Pilar', 27, 49, 'Chica sensible, extrovertida, perceptiva que cree en lo magico y lo maravilloso', 1);
INSERT INTO personajes (nombre, edad, peso, historia, pelicula_id) VALUES ('Tana', 34, 62, 'Persona constantemente malhumorada: si no protesta por el tiempo es por el gobierno; si no es por los vecinos, jovenes o jubilados', 2);
INSERT INTO personajes (nombre, edad, peso, historia, pelicula_id) VALUES ('Cora', 78, 70, 'Viuda, madre de cuatro hijos, vive en el barrio Versalles con su hijo Jorge y su nuera Susana, a quienes provoca varias incomodidades', 3);
INSERT INTO personajes (nombre, edad, peso, historia, pelicula_id) VALUES ('Kayako', 27, 45, 'Joven japonesa infelizmente casada, obsesionada con un profesor estadounidense que trabaja en japon', 4);
INSERT INTO personajes (nombre, edad, peso, historia, pelicula_id) VALUES ('Caroline', 32, 56, 'Chica de Nueva Orleans que ocupa una posicion como proveedor de cuidados de hospicio privado', 5);
INSERT INTO personajes (nombre, edad, peso, historia, pelicula_id) VALUES ('Carly', 30, 58, 'Joven amante de los animales, perdidamente enamorada de su novio Wade, aventurera e inquieta', 6);
INSERT INTO personajes (nombre, edad, peso, historia, pelicula_id) VALUES ('Charly', 45, 82, 'Existoso director de teatro de la ciudad de Nueva York', 7);
INSERT INTO personajes (nombre, edad, peso, historia, pelicula_id) VALUES ('William', 10, 32, 'Es un joven escolar que proviene de una familia de granjeros', 8);
INSERT INTO personajes (nombre, edad, peso, historia, pelicula_id) VALUES ('Riggan', 54, 85, 'Decadente actor de Hollywood famoso por su papel de superheroe Birdman en exitosas peliculas de decadas pasadas', 9);
INSERT INTO personajes (nombre, edad, peso, historia, pelicula_id) VALUES ('Tony', 52, 78, 'Fabricante, genio inventor, heroe, y ex playboy, propietario de industrias Stark', 10);
INSERT INTO personajes (nombre, edad, peso, historia, pelicula_id) VALUES ('Django', 36, 80, 'Esclavo negro vendido a los hermanos Espec', 11);