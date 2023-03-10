package com.besysoft.peliculasapp.repositories.memory;

import com.besysoft.peliculasapp.domain.entity.Pelicula;
import com.besysoft.peliculasapp.domain.entity.Personaje;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.besysoft.peliculasapp.utilities.Comparator.comparador;
import static com.besysoft.peliculasapp.utilities.DateFormatter.fechaFormatter;

//@Repository
public class PeliculaRepositoryImpl extends GenericRepository<Pelicula> implements PeliculaRepository {

    public PeliculaRepositoryImpl() {

        List<Pelicula> peliculas = new ArrayList<>(
                Arrays.asList(
                        new Pelicula(1L, "Voley", fechaFormatter(12, 3, 2015), 4, List.of(new Personaje(1L, "Pilar", 27, 49, "Chica sensible, extrovertida, perceptiva que cree en lo magico y lo maravilloso", null))),
                        new Pelicula(2L, "Un novio para mi mujer", fechaFormatter(14, 6, 2008), 4, List.of(new Personaje(2L, "Tana", 34, 62, "Persona constantemente malhumorada: si no protesta por el tiempo es por el gobierno; si no es por los vecinos, jovenes o jubilados", null))),
                        new Pelicula(3L, "Esperando la carroza", fechaFormatter(6, 5, 1985), 5, List.of(new Personaje(3L, "Cora", 78, 70, "Viuda, madre de cuatro hijos, vive en el barrio Versalles con su hijo Jorge y su nuera Susana, a quienes provoca varias incomodidades", null))),
                        new Pelicula(4L, "El grito", fechaFormatter(22, 10, 2004), 4, List.of(new Personaje(4L, "Kayako", 27, 45, "Joven japonesa infelizmente casada, obsesionada con un profesor estadounidense que trabaja en japon", null))),
                        new Pelicula(5L, "La llave maestra", fechaFormatter(12, 8, 2005), 3, List.of(new Personaje(5L, "Caroline", 32, 56, "Chica de Nueva Orleans que ocupa una posicion como proveedor de cuidados de hospicio privado", null))),
                        new Pelicula(6L, "La casa de cera", fechaFormatter(26, 4, 2005), 2, List.of(new Personaje(6L, "Carly", 30, 58, "Joven amante de los animales, perdidamente enamorada de su novio Wade, aventurera e inquieta", null))),
                        new Pelicula(7L, "Historia de un matrimonio", fechaFormatter(15, 11, 2019), 3, List.of(new Personaje(7L, "Charly", 45, 82, "Existoso director de teatro de la ciudad de Nueva York", null))),
                        new Pelicula(8L, "El ni??o que domo el viento", fechaFormatter(25, 1, 2019), 4, List.of(new Personaje(8L, "William", 10, 32, "Es un joven escolar que proviene de una familia de granjeros", null))),
                        new Pelicula(9L, "Birdman", fechaFormatter(17, 10, 2014), 5, List.of(new Personaje(9L, "Riggan", 54, 85, "Decadente actor de Hollywood famoso por su papel de superheroe Birdman en exitosas peliculas de decadas pasadas", null))),
                        new Pelicula(10L, "Avengers", fechaFormatter(11, 4, 2012), 4, List.of(new Personaje(10L, "Tony", 52, 78, "Fabricante, genio inventor, heroe, y ex playboy, propietario de industrias Stark", null))),
                        new Pelicula(11L, "Django", fechaFormatter(18, 1, 2012), 5, List.of(new Personaje(11L, "Django", 36, 80, "Esclavo negro vendido a los hermanos Espec", null)))
                ));

    }

    public List<Pelicula> findByTitulo(String titulo) {

        return this.list.stream()
                .filter(x -> comparador().equals(x.getTitulo(), titulo))
                .collect(Collectors.toList());
    }

    public List<Pelicula> findByDateBetween(Date desde, Date hasta) {

        return this.list.stream()
                .filter(x -> x.getFechaDeCreacion().after(desde) && x.getFechaDeCreacion().before(hasta))
                .collect(Collectors.toList());
    }

    public List<Pelicula> findByCalificacionBetween(Integer desde, Integer hasta) {
        return this.list.stream()
                .filter(x -> x.getCalificacion() >= desde && x.getCalificacion() <= hasta)
                .collect(Collectors.toList());
    }

    public Pelicula updatePelicula(Long id, Pelicula pelicula) {
        return this.list.stream()
                .filter(x -> x.getId().equals(id))
                .peek(x -> {
                    x.setTitulo(pelicula.getTitulo());
                    x.setFechaDeCreacion(pelicula.getFechaDeCreacion());
                    x.setCalificacion(pelicula.getCalificacion());
                    x.setPersonajes(pelicula.getPersonajes());
                })
                .findFirst()
                .orElseThrow();
    }
}
