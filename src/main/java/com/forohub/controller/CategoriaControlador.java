package com.alison.forohub.controlador;

import com.alison.forohub.modelo.Categoria;
import com.alison.forohub.repositorio.CategoriaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*") // Permite peticiones desde cualquier origen (útil para desarrollo)
public class CategoriaControlador {

    private final CategoriaRepository categoriaRepository;

    public CategoriaControlador(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    /**
     * Devuelve la lista completa de categorías.
     * Endpoint: GET /categorias
     */
    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }
}