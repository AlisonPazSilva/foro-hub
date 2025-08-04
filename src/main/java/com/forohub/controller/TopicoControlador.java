package com.forohub.controller;

import com.forohub.dto.TopicoResponse;
import com.forohub.dto.TopicoUpdateRequest;
import com.forohub.servicio.TopicoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoControlador {

    private final TopicoServicio topicoServicio;

    @Autowired
    public TopicoControlador(TopicoServicio topicoServicio) {
        this.topicoServicio = topicoServicio;
    }

    /**
     * Listar todos los tópicos con paginación.
     */
    @GetMapping
    public Page<TopicoResponse> listar(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return topicoServicio.listarTopicos(pageable);
    }

    /**
     * Obtener el detalle de un tópico por su ID.
     */
    @GetMapping("/{id}")
    public TopicoResponse obtenerPorId(@PathVariable Long id) {
        return topicoServicio.obtenerDetalle(id);
    }

    /**
     * Actualizar los datos de un tópico existente.
     */
    @PutMapping("/{id}")
    public TopicoResponse actualizar(
            @PathVariable Long id,
            @RequestBody @Valid TopicoUpdateRequest request
    ) {
        return topicoServicio.actualizarTopico(id, request);
    }

    /**
     * Eliminar un tópico por su ID.
     */
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        topicoServicio.eliminarTopico(id);
    }
}