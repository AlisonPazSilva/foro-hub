package com.forohub.controller;

import com.forohub.dto.UsuarioRequest;
import com.forohub.dto.UsuarioResponse;
import com.forohub.modelo.Usuario;
import com.forohub.repositorio.UsuarioRepositorio;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioRepositorio repositorio;

    public UsuarioController(UsuarioRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    // GET: Listar todos los usuarios
    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listarUsuarios() {
        List<UsuarioResponse> usuarios = repositorio.findAll()
                .stream()
                .map(UsuarioResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(usuarios);
    }

    // POST: Crear un nuevo usuario
    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody UsuarioRequest request) {
        if (repositorio.existsByEmail(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe un usuario con ese email.");
        }

        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .email(request.getEmail())
                .password(request.getPassword())
                .role("USER")
                .build();

        Usuario nuevoUsuario = repositorio.save(usuario);
        UsuarioResponse response = new UsuarioResponse(nuevoUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

