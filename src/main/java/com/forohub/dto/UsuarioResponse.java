package com.forohub.dto;

import com.forohub.modelo.Usuario;
import lombok.Getter;

@Getter
public class UsuarioResponse {

    private Long id;
    private String nombre;
    private String email;
    private String role;

    public UsuarioResponse(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.email = usuario.getEmail();
        this.role = usuario.getRole();
    }
}