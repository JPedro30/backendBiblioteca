package com.susana.backendBiblioteca.controller;

import com.susana.backendBiblioteca.model.LibroModel;
import com.susana.backendBiblioteca.services.BibliotecaService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/libros")
public class BibliotecaController {

    @Autowired
    BibliotecaService bibliotecaService;

    // CRUD BASICO

    // SELECT ALL
    @GetMapping()
    public List<LibroModel> buscarLibros() {
        return bibliotecaService.buscarLibros();
    }
    
    // CREATE
    @PostMapping()
    public LibroModel guardarLibro(@RequestBody LibroModel libro) {
        return bibliotecaService.guardarLibro(libro);
    }

    // UPDATE
    @PutMapping()
    public LibroModel actualizarLibro(@RequestBody LibroModel libro) {
        return bibliotecaService.guardarLibro(libro);
    }

    // DELETE
    @DeleteMapping("/{idLibro}")
    public boolean borrarLibro(@PathVariable("idLibro") Long idLibro) {
        if (bibliotecaService.existeLibro(idLibro)) {
            bibliotecaService.borrarLibro(idLibro);
            return true;
        } else return false;
    }


    // SELECT ESPECIFICOS

    // TODOS LOS AUTORES
    @GetMapping("/buscar/autores")    
    public List<String> buscarAutores(){
        return bibliotecaService.obtenerAutores();
    }

    // AUTOR ESPECIFICO
    @GetMapping("/buscar/autores/{autor}")    
    public List<LibroModel> buscarAutor(@PathVariable("autor") String autor){
        return bibliotecaService.buscarAutor(autor);
    }

    // TODOS LOS OWNERS
    @GetMapping("/buscar/owners")    
    public List<String> buscarOwners(){
        return bibliotecaService.obtenerOwners();
    }

    // OWNER ESPECIFICO
    @GetMapping("/buscar/owners/{owner}")    
    public List<LibroModel> buscarOwner(@PathVariable("owner") String owner){
        return bibliotecaService.buscarOwner(owner);
    }

    // TODOS LOS GENEROS
    @GetMapping("/buscar/generos")    
    public List<String> obtenerGeneros(){
        return bibliotecaService.obtenerGeneros();
    }

    // GENERO ESPECIFICO
    @GetMapping("/buscar/generos/{genero}")    
    public List<LibroModel> buscarGenero(@PathVariable("genero") String genero){
        return bibliotecaService.buscarGenero(genero);
    }

    // TITULO ESPECIFICO
    @GetMapping("/buscar/titulo/{titulo}")    
    public List<LibroModel> buscarTitulo(@PathVariable("titulo") String titulo){
        return bibliotecaService.buscarTitulo(titulo);
    }

    @GetMapping("/buscar/titulo/comienzo/{letra}")
    public List<LibroModel> buscarTituloPorLetra(@PathVariable("letra") String letra) {
        return bibliotecaService.buscarTituloComienzaLetra(letra);
    }
    

}
