package com.besysoft.ejerciciounidad6.utilities;

import com.besysoft.ejerciciounidad6.domain.entity.Genero;
import com.besysoft.ejerciciounidad6.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad6.domain.entity.Personaje;
import com.besysoft.ejerciciounidad6.repositories.memory.GeneroRepositoryImpl;
import com.besysoft.ejerciciounidad6.repositories.memory.PeliculaRepositoryImpl;
import com.besysoft.ejerciciounidad6.repositories.memory.PersonajeRepositoryImpl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Deprecated
//@Component
public class LoadData {

    private final GeneroRepositoryImpl generoRepositoryImpl;

    private final PeliculaRepositoryImpl peliculaRepository;

    private final PersonajeRepositoryImpl personajeRepository;

    public LoadData(GeneroRepositoryImpl generoRepositoryImpl, PeliculaRepositoryImpl peliculaRepository, PersonajeRepositoryImpl personajeRepository) {
        this.generoRepositoryImpl = generoRepositoryImpl;
        this.peliculaRepository = peliculaRepository;
        this.personajeRepository = personajeRepository;

        this.init();
    }

    public Date fechaFormatter(int dia, int mes, int año) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(año, mes - 1, dia);
        return calendar.getTime();
    }

    private void init() {
        Personaje personaje1 = this.personajeRepository.save(new Personaje(1L, "Pilar", 27, 49, "Chica sensible, extrovertida, perceptiva que cree en lo magico y lo maravilloso", null));
        Personaje personaje2 = this.personajeRepository.save(new Personaje(2L, "Tana", 34, 62, "Persona constantemente malhumorada: si no protesta por el tiempo es por el gobierno; si no es por los vecinos, jovenes o jubilados", null));
        Personaje personaje3 = this.personajeRepository.save(new Personaje(3L, "Cora", 78, 70, "Viuda, madre de cuatro hijos, vive en el barrio Versalles con su hijo Jorge y su nuera Susana, a quienes provoca varias incomodidades", null));
        Personaje personaje4 = this.personajeRepository.save(new Personaje(4L, "Kayako", 27, 45, "Joven japonesa infelizmente casada, obsesionada con un profesor estadounidense que trabaja en japon", null));
        Personaje personaje5 = this.personajeRepository.save(new Personaje(5L, "Caroline", 32, 56, "Chica de Nueva Orleans que ocupa una posicion como proveedor de cuidados de hospicio privado", null));
        Personaje personaje6 = this.personajeRepository.save(new Personaje(6L, "Carly", 30, 58, "Joven amante de los animales, perdidamente enamorada de su novio Wade, aventurera e inquieta", null));
        Personaje personaje7 = this.personajeRepository.save(new Personaje(7L, "Charly", 45, 82, "Existoso director de teatro de la ciudad de Nueva York", null));
        Personaje personaje8 = this.personajeRepository.save(new Personaje(8L, "William", 10, 32, "Es un joven escolar que proviene de una familia de granjeros", null));
        Personaje personaje9 = this.personajeRepository.save(new Personaje(9L, "Riggan", 54, 85, "Decadente actor de Hollywood famoso por su papel de superheroe Birdman en exitosas peliculas de decadas pasadas", null));
        Personaje personaje10 = this.personajeRepository.save(new Personaje(10L, "Tony", 52, 78, "Fabricante, genio inventor, heroe, y ex playboy, propietario de industrias Stark", null));
        Personaje personaje11 = this.personajeRepository.save(new Personaje(11L, "Django", 36, 80, "Esclavo negro vendido a los hermanos Espec", null));

        Pelicula pelicula1 = this.peliculaRepository.save(new Pelicula(1L, "Voley", fechaFormatter(12, 3, 2015), 4, List.of(personaje1)));
        Pelicula pelicula2 = this.peliculaRepository.save(new Pelicula(2L, "Un novio para mi mujer", fechaFormatter(14, 6, 2008), 4, List.of(personaje2)));
        Pelicula pelicula3 = this.peliculaRepository.save(new Pelicula(3L, "Esperando la carroza", fechaFormatter(06, 05, 1985), 5, List.of(personaje3)));
        Pelicula pelicula4 = this.peliculaRepository.save(new Pelicula(4L, "El grito", fechaFormatter(22, 10, 2004), 4, List.of(personaje4)));
        Pelicula pelicula5 = this.peliculaRepository.save(new Pelicula(5L, "La llave maestra", fechaFormatter(12, 8, 2005), 3, List.of(personaje5)));
        Pelicula pelicula6 = this.peliculaRepository.save(new Pelicula(6L, "La casa de cera", fechaFormatter(26, 4, 2005), 2, List.of(personaje6)));
        Pelicula pelicula7 = this.peliculaRepository.save(new Pelicula(7L, "Historia de un matrimonio", fechaFormatter(15, 11, 2019), 3, List.of(personaje7)));
        Pelicula pelicula8 = this.peliculaRepository.save(new Pelicula(8L, "El niño que domo el viento", fechaFormatter(25, 01, 2019), 4, List.of(personaje8)));
        Pelicula pelicula9 = this.peliculaRepository.save(new Pelicula(9L, "Birdman", fechaFormatter(17, 10, 2014), 5, List.of(personaje9)));
        Pelicula pelicula10 = this.peliculaRepository.save(new Pelicula(10L, "Avengers", fechaFormatter(11, 4, 2012), 4, List.of(personaje10)));
        Pelicula pelicula11 = this.peliculaRepository.save(new Pelicula(11L, "Django", fechaFormatter(18, 1, 2012), 5, List.of(personaje11)));

        personaje1.setPelicula(pelicula1);
        personaje2.setPelicula(pelicula2);
        personaje3.setPelicula(pelicula3);
        personaje4.setPelicula(pelicula4);
        personaje5.setPelicula(pelicula5);
        personaje6.setPelicula(pelicula6);
        personaje7.setPelicula(pelicula7);
        personaje8.setPelicula(pelicula8);
        personaje9.setPelicula(pelicula9);
        personaje10.setPelicula(pelicula10);
        personaje11.setPelicula(pelicula11);

        this.generoRepositoryImpl.save(new Genero(1L, "Comedia", List.of(pelicula1, pelicula2, pelicula3)));
        this.generoRepositoryImpl.save(new Genero(2L, "Terror", List.of(pelicula4, pelicula5, pelicula6)));
        this.generoRepositoryImpl.save(new Genero(3L, "Drama", List.of(pelicula7, pelicula8, pelicula9)));
        this.generoRepositoryImpl.save(new Genero(4L, "Acción", List.of(pelicula10, pelicula11)));

    }

}
