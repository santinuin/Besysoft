package com.besysoft.ejerciciounidad5.repositories.memory;

import com.besysoft.ejerciciounidad5.domain.entity.Pelicula;
import com.besysoft.ejerciciounidad5.domain.entity.Personaje;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.besysoft.ejerciciounidad5.utilities.Comparator.comparador;
import static com.besysoft.ejerciciounidad5.utilities.DateFormatter.fechaFormatter;

//@Repository
public class PersonajeRepositoryImpl extends GenericRepository<Personaje> implements PersonajeRepository {

    public PersonajeRepositoryImpl() {

        List<Personaje> personajes = new ArrayList<>(
                Arrays.asList(
                        new Personaje(1L, "Pilar", 27, 49, "Chica sensible, extrovertida, perceptiva que cree en lo magico y lo maravilloso", null),
                        new Personaje(2L, "Tana", 34, 62, "Persona constantemente malhumorada: si no protesta por el tiempo es por el gobierno; si no es por los vecinos, jovenes o jubilados", null),
                        new Personaje(3L, "Cora", 78, 70, "Viuda, madre de cuatro hijos, vive en el barrio Versalles con su hijo Jorge y su nuera Susana, a quienes provoca varias incomodidades", null),
                        new Personaje(4L, "Kayako", 27, 45, "Joven japonesa infelizmente casada, obsesionada con un profesor estadounidense que trabaja en japon", null),
                        new Personaje(5L, "Caroline", 32, 56, "Chica de Nueva Orleans que ocupa una posicion como proveedor de cuidados de hospicio privado", null),
                        new Personaje(6L, "Carly", 30, 58, "Joven amante de los animales, perdidamente enamorada de su novio Wade, aventurera e inquieta", null),
                        new Personaje(7L, "Charly", 45, 82, "Existoso director de teatro de la ciudad de Nueva York", null),
                        new Personaje(8L, "William", 10, 32, "Es un joven escolar que proviene de una familia de granjeros", null),
                        new Personaje(9L, "Riggan", 54, 85, "Decadente actor de Hollywood famoso por su papel de superheroe Birdman en exitosas peliculas de decadas pasadas", null),
                        new Personaje(10L, "Tony", 52, 78, "Fabricante, genio inventor, heroe, y ex playboy, propietario de industrias Stark", null),
                        new Personaje(11L, "Django", 36, 80, "Esclavo negro vendido a los hermanos Espec", null)));

        personajes.get(0).setPelicula(new Pelicula(1L, "Voley", fechaFormatter(12, 3, 2015), 4, null));
        personajes.get(1).setPelicula(new Pelicula(2L, "Un novio para mi mujer", fechaFormatter(14, 6, 2008), 4, null));
        personajes.get(2).setPelicula(new Pelicula(3L, "Esperando la carroza", fechaFormatter(6, 5, 1985), 5, null));
        personajes.get(3).setPelicula(new Pelicula(4L, "El grito", fechaFormatter(22, 10, 2004), 4, null));
        personajes.get(4).setPelicula(new Pelicula(5L, "La llave maestra", fechaFormatter(12, 8, 2005), 3, null));
        personajes.get(5).setPelicula(new Pelicula(6L, "La casa de cera", fechaFormatter(26, 4, 2005), 2, null));
        personajes.get(6).setPelicula(new Pelicula(7L, "Historia de un matrimonio", fechaFormatter(15, 11, 2019), 3, null));
        personajes.get(7).setPelicula(new Pelicula(8L, "El niño que domo el viento", fechaFormatter(25, 1, 2019), 4, null));
        personajes.get(8).setPelicula(new Pelicula(9L, "Birdman", fechaFormatter(17, 10, 2014), 5, null));
        personajes.get(9).setPelicula(new Pelicula(10L, "Avengers", fechaFormatter(11, 4, 2012), 4, null));
        personajes.get(10).setPelicula(new Pelicula(11L, "Django", fechaFormatter(18, 1, 2012), 5, null));

    }

    public List<Personaje> findByNombre(String nombre) {

        return this.list.stream()
                .filter(x -> comparador().equals(x.getNombre(), nombre))
                .collect(Collectors.toList());
    }

    public List<Personaje> findByEdad(Integer edad) {
        return this.list.stream()
                .filter(x -> x.getEdad().compareTo(edad) == 0)
                .collect(Collectors.toList());
    }

    public List<Personaje> findByEdadBetween(Integer desde, Integer hasta) {
        return this.list.stream()
                .filter(x -> x.getEdad() >= desde && x.getEdad() <= hasta)
                .collect(Collectors.toList());
    }

    public Personaje updatePersonaje(Long id, Personaje personaje) {

        return this.list.stream()
                .filter(x -> x.getId().equals(id))
                .peek(x -> {
                    x.setNombre(personaje.getNombre());
                    x.setEdad(personaje.getEdad());
                    x.setPeso(personaje.getPeso());
                    x.setHistoria(personaje.getHistoria());
                    x.setPelicula(personaje.getPelicula());
                })
                .findFirst()
                .orElseThrow();

    }
}
