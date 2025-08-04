package com.forohub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TopicoRequest {

    @NotBlank(message = "El título es obligatorio")
    private String titulo;

    @NotBlank(message = "El mensaje es obligatorio")
    private String mensaje;

    @NotNull(message = "El ID del autor es obligatorio")
    private Long autorId;

    @NotNull(message = "El ID del curso es obligatorio")
    private Long cursoId;

    public TopicoRequest() {}

    public TopicoRequest(String titulo, String mensaje, Long autorId, Long cursoId) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.autorId = autorId;
        this.cursoId = cursoId;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public Long getAutorId() { return autorId; }
    public void setAutorId(Long autorId) { this.autorId = autorId; }

    public Long getCursoId() { return cursoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }
}