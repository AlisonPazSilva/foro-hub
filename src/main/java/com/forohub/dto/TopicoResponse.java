package com.forohub.dto;

import java.time.LocalDateTime;

public class TopicoResponse {

    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private String status;
    private String autorNombre;
    private String cursoNombre;

    public TopicoResponse(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion,
                          String status, String autorNombre, String cursoNombre) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = fechaCreacion;
        this.status = status;
        this.autorNombre = autorNombre;
        this.cursoNombre = cursoNombre;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getMensaje() { return mensaje; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public String getStatus() { return status; }
    public String getAutorNombre() { return autorNombre; }
    public String getCursoNombre() { return cursoNombre; }
}