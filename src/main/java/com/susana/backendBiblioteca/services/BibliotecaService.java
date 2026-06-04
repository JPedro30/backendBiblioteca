package com.susana.backendBiblioteca.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.susana.backendBiblioteca.model.LibroModel;
import com.susana.backendBiblioteca.repository.BibliotecaRepository;

@Service
public class BibliotecaService {
    @Autowired
    BibliotecaRepository bibliotecaRepository;

    // CRUD BASICOS

    // SELECT ALL
    public List<LibroModel> buscarLibros(){
        return (List<LibroModel>)bibliotecaRepository.getAllOrderByTituloAsc();
    }

    // GUARDAR LIBRO
    public LibroModel guardarLibro(LibroModel libro){
        return bibliotecaRepository.save(libro);
    }

    // ACTUALIZAR LIBRO
    public LibroModel actualizarLibro (LibroModel libro){
        return bibliotecaRepository.save(libro);
    }

    // BORRAR LIBRO
    public void borrarLibro(Long idLibro){
        bibliotecaRepository.deleteById(idLibro);
    }

    // EXISTE LIBRO POR ID
    public boolean existeLibro(Long idLibro){
        return bibliotecaRepository.existsById(idLibro);
    }
    
    // BUSQUEDAS ESPECIFICAS

    // TODOS LOS AUTORES
    public List<String> obtenerAutores() {
        return bibliotecaRepository.getByAutorDistinct();
    }

    // AUTOR ESPECIFICO
    public List<LibroModel> buscarAutor(String autor){
        return bibliotecaRepository.getByAutor(autor);
    }

    // TODOS LOS OWNERS
    public List<String> obtenerOwners(){
        return bibliotecaRepository.getByOwnerDistinct();
    }

    // OWNER ESPECIFICO
    public List<LibroModel> buscarOwner(String owner){
        return bibliotecaRepository.getByOwner(owner);
    }

    // TODOS LOS GENEROS
    public List<String> obtenerGeneros(){
        return bibliotecaRepository.getByGeneroDistinct();
    }

    // GENERO ESPECIFICO
    public List<LibroModel> buscarGenero(String genero){
        return bibliotecaRepository.getByGenero(genero);
    }

    // TITULO ESPECIFICO
    public List<LibroModel> buscarTitulo(String titulo){
        return bibliotecaRepository.getByTituloContainingIgnoreCase(titulo);
    }

    // TITULO QUE COMIENZA POR LETRA ESPECIFICA
    public List<LibroModel> buscarTituloComienzaLetra(String letra){
        return bibliotecaRepository.getByTituloStartsWithIgnoreCase(letra);
    }
    
}
