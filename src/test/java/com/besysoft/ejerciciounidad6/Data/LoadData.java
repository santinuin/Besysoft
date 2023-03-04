package com.besysoft.ejerciciounidad6.Data;

import com.besysoft.ejerciciounidad6.domain.entity.Genero;
import com.besysoft.ejerciciounidad6.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad6.domain.entity.Personaje;
import com.besysoft.ejerciciounidad6.dto.GeneroDTO;
import com.besysoft.ejerciciounidad6.dto.PeliculaDTO;
import com.besysoft.ejerciciounidad6.dto.PersonajeDTO;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LoadData {
    public LoadData() {
        this.setPeliculasToPersonajes();
    }

    public static Date fechaFormatter(int dia, int mes, int año) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(año, mes - 1, dia);
        return calendar.getTime();
    }

    //ENTITIES

    public static final Personaje PERSONAJE_1 = new Personaje(1L, "Pilar", 27, 49, "Chica sensible, extrovertida, perceptiva que cree en lo magico y lo maravilloso", null);
    public static final Personaje PERSONAJE_2 = new Personaje(2L, "Tana", 34, 62, "Persona constantemente malhumorada: si no protesta por el tiempo es por el gobierno; si no es por los vecinos, jovenes o jubilados", null);
    public static final Personaje PERSONAJE_3 = new Personaje(3L, "Cora", 78, 70, "Viuda, madre de cuatro hijos, vive en el barrio Versalles con su hijo Jorge y su nuera Susana, a quienes provoca varias incomodidades", null);
    public static final Personaje PERSONAJE_4 = new Personaje(4L, "Kayako", 27, 45, "Joven japonesa infelizmente casada, obsesionada con un profesor estadounidense que trabaja en japon", null);
    public static final Personaje PERSONAJE_5 = new Personaje(5L, "Caroline", 32, 56, "Chica de Nueva Orleans que ocupa una posicion como proveedor de cuidados de hospicio privado", null);
    public static final Personaje PERSONAJE_6 = new Personaje(6L, "Carly", 30, 58, "Joven amante de los animales, perdidamente enamorada de su novio Wade, aventurera e inquieta", null);
    public static final Personaje PERSONAJE_7 = new Personaje(7L, "Charly", 45, 82, "Existoso director de teatro de la ciudad de Nueva York", null);
    public static final Personaje PERSONAJE_8 = new Personaje(8L, "William", 10, 32, "Es un joven escolar que proviene de una familia de granjeros", null);
    public static final Personaje PERSONAJE_9 = new Personaje(9L, "Riggan", 54, 85, "Decadente actor de Hollywood famoso por su papel de superheroe Birdman en exitosas peliculas de decadas pasadas", null);
    public static final Personaje PERSONAJE_10 = new Personaje(10L, "Tony", 52, 78, "Fabricante, genio inventor, heroe, y ex playboy, propietario de industrias Stark", null);
    public static final Personaje PERSONAJE_11 = new Personaje(11L, "Django", 36, 80, "Esclavo negro vendido a los hermanos Espec", null);

    public static final Pelicula PELICULA_1 = new Pelicula(1L, "Voley", fechaFormatter(12, 3, 2015), 4, List.of(PERSONAJE_1));
    public static final Pelicula PELICULA_2 = new Pelicula(2L, "Un novio para mi mujer", fechaFormatter(14, 6, 2008), 4, List.of(PERSONAJE_2));
    public static final Pelicula PELICULA_3 = new Pelicula(3L, "Esperando la carroza", fechaFormatter(06, 05, 1985), 5, List.of(PERSONAJE_3));
    public static final Pelicula PELICULA_4 = new Pelicula(4L, "El grito", fechaFormatter(22, 10, 2004), 4, List.of(PERSONAJE_4));
    public static final Pelicula PELICULA_5 = new Pelicula(5L, "La llave maestra", fechaFormatter(12, 8, 2005), 3, List.of(PERSONAJE_5));
    public static final Pelicula PELICULA_6 = new Pelicula(6L, "La casa de cera", fechaFormatter(26, 4, 2005), 2, List.of(PERSONAJE_6));
    public static final Pelicula PELICULA_7 = new Pelicula(7L, "Historia de un matrimonio", fechaFormatter(15, 11, 2019), 3, List.of(PERSONAJE_7));
    public static final Pelicula PELICULA_8 = new Pelicula(8L, "El niño que domo el viento", fechaFormatter(25, 01, 2019), 4, List.of(PERSONAJE_8));
    public static final Pelicula PELICULA_9 = new Pelicula(9L, "Birdman", fechaFormatter(17, 10, 2014), 5, List.of(PERSONAJE_9));
    public static final Pelicula PELICULA_10 = new Pelicula(10L, "Avengers", fechaFormatter(11, 4, 2012), 4, List.of(PERSONAJE_10));
    public static final Pelicula PELICULA_11 = new Pelicula(11L, "Django", fechaFormatter(18, 1, 2012), 5, List.of(PERSONAJE_11));

    private static void setPeliculasToPersonajes() {
        PERSONAJE_1.setPelicula(PELICULA_1);
        PERSONAJE_2.setPelicula(PELICULA_2);
        PERSONAJE_3.setPelicula(PELICULA_3);
        PERSONAJE_4.setPelicula(PELICULA_4);
        PERSONAJE_5.setPelicula(PELICULA_5);
        PERSONAJE_6.setPelicula(PELICULA_6);
        PERSONAJE_7.setPelicula(PELICULA_7);
        PERSONAJE_8.setPelicula(PELICULA_8);
        PERSONAJE_9.setPelicula(PELICULA_9);
        PERSONAJE_10.setPelicula(PELICULA_10);
        PERSONAJE_11.setPelicula(PELICULA_11);
    }

    public static final Genero GENERO_1 = new Genero(1L, "Comedia", List.of(PELICULA_1, PELICULA_2, PELICULA_3));
    public static final Genero GENERO_2 = new Genero(2L, "Terror", List.of(PELICULA_4, PELICULA_5, PELICULA_6));
    public static final Genero GENERO_3 = new Genero(3L, "Drama", List.of(PELICULA_7, PELICULA_8, PELICULA_9));
    public static final Genero GENERO_4 = new Genero(4L, "Acción", List.of(PELICULA_10, PELICULA_11));

    public static final List<Genero> GENEROS = List.of(GENERO_1, GENERO_2, GENERO_3, GENERO_4);
    public static final List<Pelicula> PELICULAS = List.of(PELICULA_1, PELICULA_2, PELICULA_3, PELICULA_4, PELICULA_5, PELICULA_6, PELICULA_7, PELICULA_8, PELICULA_9, PELICULA_10, PELICULA_11);
    public static final List<Personaje> PERSONAJES = List.of(PERSONAJE_1, PERSONAJE_2, PERSONAJE_3, PERSONAJE_4, PERSONAJE_5, PERSONAJE_6, PERSONAJE_7, PERSONAJE_8, PERSONAJE_9, PERSONAJE_10, PERSONAJE_11);


    //DTOS

    public static final PersonajeDTO PERSONAJE_DTO_1 = new PersonajeDTO(1L, "Pilar", 27, 49, "Chica sensible, extrovertida, perceptiva que cree en lo magico y lo maravilloso", null);
    public static final PersonajeDTO PERSONAJE_DTO_2 = new PersonajeDTO(2L, "Tana", 34, 62, "Persona constantemente malhumorada: si no protesta por el tiempo es por el gobierno; si no es por los vecinos, jovenes o jubilados", null);
    public static final PersonajeDTO PERSONAJE_DTO_3 = new PersonajeDTO(3L, "Cora", 78, 70, "Viuda, madre de cuatro hijos, vive en el barrio Versalles con su hijo Jorge y su nuera Susana, a quienes provoca varias incomodidades", null);
    public static final PersonajeDTO PERSONAJE_DTO_4 = new PersonajeDTO(4L, "Kayako", 27, 45, "Joven japonesa infelizmente casada, obsesionada con un profesor estadounidense que trabaja en japon", null);
    public static final PersonajeDTO PERSONAJE_DTO_5 = new PersonajeDTO(5L, "Caroline", 32, 56, "Chica de Nueva Orleans que ocupa una posicion como proveedor de cuidados de hospicio privado", null);
    public static final PersonajeDTO PERSONAJE_DTO_6 = new PersonajeDTO(6L, "Carly", 30, 58, "Joven amante de los animales, perdidamente enamorada de su novio Wade, aventurera e inquieta", null);
    public static final PersonajeDTO PERSONAJE_DTO_7 = new PersonajeDTO(7L, "Charly", 45, 82, "Existoso director de teatro de la ciudad de Nueva York", null);
    public static final PersonajeDTO PERSONAJE_DTO_8 = new PersonajeDTO(8L, "William", 10, 32, "Es un joven escolar que proviene de una familia de granjeros", null);
    public static final PersonajeDTO PERSONAJE_DTO_9 = new PersonajeDTO(9L, "Riggan", 54, 85, "Decadente actor de Hollywood famoso por su papel de superheroe Birdman en exitosas peliculas de decadas pasadas", null);
    public static final PersonajeDTO PERSONAJE_DTO_10 = new PersonajeDTO(10L, "Tony", 52, 78, "Fabricante, genio inventor, heroe, y ex playboy, propietario de industrias Stark", null);
    public static final PersonajeDTO PERSONAJE_DTO_11 = new PersonajeDTO(11L, "Django", 36, 80, "Esclavo negro vendido a los hermanos Espec", null);

    public static final PeliculaDTO PELICULA_DTO_1 = new PeliculaDTO(1L, "Voley", fechaFormatter(12, 3, 2015), 4, List.of(PERSONAJE_1));
    public static final PeliculaDTO PELICULA_DTO_2 = new PeliculaDTO(2L, "Un novio para mi mujer", fechaFormatter(14, 6, 2008), 4, List.of(PERSONAJE_2));
    public static final PeliculaDTO PELICULA_DTO_3 = new PeliculaDTO(3L, "Esperando la carroza", fechaFormatter(06, 05, 1985), 5, List.of(PERSONAJE_3));
    public static final PeliculaDTO PELICULA_DTO_4 = new PeliculaDTO(4L, "El grito", fechaFormatter(22, 10, 2004), 4, List.of(PERSONAJE_4));
    public static final PeliculaDTO PELICULA_DTO_5 = new PeliculaDTO(5L, "La llave maestra", fechaFormatter(12, 8, 2005), 3, List.of(PERSONAJE_5));
    public static final PeliculaDTO PELICULA_DTO_6 = new PeliculaDTO(6L, "La casa de cera", fechaFormatter(26, 4, 2005), 2, List.of(PERSONAJE_6));
    public static final PeliculaDTO PELICULA_DTO_7 = new PeliculaDTO(7L, "Historia de un matrimonio", fechaFormatter(15, 11, 2019), 3, List.of(PERSONAJE_7));
    public static final PeliculaDTO PELICULA_DTO_8 = new PeliculaDTO(8L, "El niño que domo el viento", fechaFormatter(25, 01, 2019), 4, List.of(PERSONAJE_8));
    public static final PeliculaDTO PELICULA_DTO_9 = new PeliculaDTO(9L, "Birdman", fechaFormatter(17, 10, 2014), 5, List.of(PERSONAJE_9));
    public static final PeliculaDTO PELICULA_DTO_10 = new PeliculaDTO(10L, "Avengers", fechaFormatter(11, 4, 2012), 4, List.of(PERSONAJE_10));
    public static final PeliculaDTO PELICULA_DTO_11 = new PeliculaDTO(11L, "Django", fechaFormatter(18, 1, 2012), 5, List.of(PERSONAJE_11));

    private static void setPeliculasToPersonajesDTO() {
        PERSONAJE_DTO_1.setPelicula(PELICULA_1);
        PERSONAJE_DTO_2.setPelicula(PELICULA_2);
        PERSONAJE_DTO_3.setPelicula(PELICULA_3);
        PERSONAJE_DTO_4.setPelicula(PELICULA_4);
        PERSONAJE_DTO_5.setPelicula(PELICULA_5);
        PERSONAJE_DTO_6.setPelicula(PELICULA_6);
        PERSONAJE_DTO_7.setPelicula(PELICULA_7);
        PERSONAJE_DTO_8.setPelicula(PELICULA_8);
        PERSONAJE_DTO_9.setPelicula(PELICULA_9);
        PERSONAJE_DTO_10.setPelicula(PELICULA_10);
        PERSONAJE_DTO_11.setPelicula(PELICULA_11);
    }

    public static final GeneroDTO GENERO_DTO_1 = new GeneroDTO(1L, "Comedia", List.of(PELICULA_1, PELICULA_2, PELICULA_3));
    public static final GeneroDTO GENERO_DTO_2 = new GeneroDTO(2L, "Terror", List.of(PELICULA_4, PELICULA_5, PELICULA_6));
    public static final GeneroDTO GENERO_DTO_3 = new GeneroDTO(3L, "Drama", List.of(PELICULA_7, PELICULA_8, PELICULA_9));
    public static final GeneroDTO GENERO_DTO_4 = new GeneroDTO(4L, "Acción", List.of(PELICULA_10, PELICULA_11));

    public static final List<GeneroDTO> GENEROS_DTO = List.of(GENERO_DTO_1, GENERO_DTO_2, GENERO_DTO_3, GENERO_DTO_4);
    public static final List<PeliculaDTO> PELICULAS_DTO = List.of(PELICULA_DTO_1, PELICULA_DTO_2, PELICULA_DTO_3, PELICULA_DTO_4, PELICULA_DTO_5, PELICULA_DTO_6, PELICULA_DTO_7, PELICULA_DTO_8, PELICULA_DTO_9, PELICULA_DTO_10, PELICULA_DTO_11);
    public static final List<PersonajeDTO> PERSONAJES_DTO = List.of(PERSONAJE_DTO_1, PERSONAJE_DTO_2, PERSONAJE_DTO_3, PERSONAJE_DTO_4, PERSONAJE_DTO_5, PERSONAJE_DTO_6, PERSONAJE_DTO_7, PERSONAJE_DTO_8, PERSONAJE_DTO_9, PERSONAJE_DTO_10, PERSONAJE_DTO_11);


}
