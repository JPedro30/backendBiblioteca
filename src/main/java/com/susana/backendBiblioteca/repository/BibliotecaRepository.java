package com.susana.backendBiblioteca.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.susana.backendBiblioteca.model.LibroModel;
import java.util.List;


@Repository
public interface BibliotecaRepository extends CrudRepository<LibroModel, Long>{

    // SELECT ALL
    List<LibroModel> findAllByOrderByTituloAsc();

    // -------------------------------------------------------------------
    // 1. ORDENAR LOS RESULTADOS DE BÚSQUEDA (Los libros que se muestran)
    // -------------------------------------------------------------------

    // Añadimos 'OrderByTituloAsc' al final de los métodos mágicos de Spring
    List<LibroModel> getByTituloContainingIgnoreCaseOrderByTituloAsc(String titulo);
    
    List<LibroModel> getByTituloStartsWithIgnoreCaseOrderByTituloAsc(String letra);

    List<LibroModel> getByAutorOrderByTituloAsc(String autor);

    // En las querys nativas, metemos el ORDER BY directamente en el SQL
    @Query(value = "SELECT * FROM libro WHERE :genero = ANY(genero) ORDER BY titulo ASC", nativeQuery = true)
    List<LibroModel> getByGenero(@Param("genero") String genero);

    @Query(value = "SELECT * FROM libro WHERE :owner = ANY(owner) ORDER BY titulo ASC", nativeQuery = true)
    List<LibroModel> getByOwner(@Param("owner") String owner);


    // -------------------------------------------------------------------
    // 2. ORDENAR LAS LISTAS DESPLEGABLES (Autores, dueños y géneros)
    // -------------------------------------------------------------------

    // Al usar unnest, le ponemos un "alias" (AS nombre) y lo ordenamos por ese alias
    @Query(value = "SELECT DISTINCT unnest(genero) AS nombre FROM libro ORDER BY nombre ASC", nativeQuery = true)
    List<String> getByGeneroDistinct();

    @Query(value = "SELECT DISTINCT unnest(owner) AS nombre FROM libro ORDER BY nombre ASC", nativeQuery = true)
    List<String> getByOwnerDistinct();

    // En la query de Java (JPQL), simplemente le decimos que ordene por l.autor
    @Query(value = "SELECT DISTINCT l.autor FROM LibroModel l ORDER BY l.autor ASC")
    List<String> getByAutorDistinct();

}