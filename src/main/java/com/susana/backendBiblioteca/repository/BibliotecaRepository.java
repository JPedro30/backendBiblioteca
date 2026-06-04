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
    List<LibroModel> getAllOrderByTituloAsc();

    // SELECT DE TITULO APROXIMADO
    List<LibroModel> getByTituloContainingIgnoreCase(String titulo);
    
    // SELECT DE TITULO QUE COMIENZA CON LETRA ESPECIFICA
    List<LibroModel> getByTituloStartsWithIgnoreCase(String letra);

    // SELECT DE GENERO EXACTO
    @Query(value = "SELECT * FROM libro WHERE :genero = ANY(genero)", nativeQuery = true)
    List<LibroModel> getByGenero(@Param("genero") String genero);

    // SELECT DE OWNER EXACTO
    @Query(value = "SELECT * FROM libro WHERE :owner = ANY(owner)", nativeQuery = true)
    List<LibroModel> getByOwner(@Param("owner") String owner);

    // SELECT DE AUTOR EXACTO
    List<LibroModel> getByAutor(String autor);

    // SELECT DE TODOS LOS GENEROS
    @Query(value = "SELECT DISTINCT unnest(genero) FROM libro", nativeQuery = true)
    List<String> getByGeneroDistinct();

    // SELECT DE TODOS LOS OWNERS
    @Query(value = "SELECT DISTINCT unnest(owner) FROM libro", nativeQuery = true)
    List<String> getByOwnerDistinct();

    // SELECT DE TODOS LOS AUTORES
    @Query(value = "SELECT DISTINCT l.autor FROM LibroModel l")
    List<String> getByAutorDistinct();

}
