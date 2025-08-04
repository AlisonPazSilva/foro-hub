package com.forohub.servicio;

import com.forohub.dto.TopicoRequest;
import com.forohub.dto.TopicoResponse;
import com.forohub.dto.TopicoUpdateRequest;
import com.forohub.modelo.Curso;
import com.forohub.modelo.Topico;
import com.forohub.modelo.Usuario;
import com.forohub.modelo.EstadoTopico;
import com.forohub.repositorio.CursoRepositorio;
import com.forohub.repositorio.TopicoRepositorio;
import com.forohub.repositorio.UsuarioRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@Service
public class TopicoServicio {

    private final TopicoRepositorio topicoRepositorio;
    private final UsuarioRepositorio usuarioRepositorio;
    private final CursoRepositorio cursoRepositorio;

    public TopicoServicio(TopicoRepositorio topicoRepositorio,
                          UsuarioRepositorio usuarioRepositorio,
                          CursoRepositorio cursoRepositorio) {
        this.topicoRepositorio = topicoRepositorio;
        this.usuarioRepositorio = usuarioRepositorio;
        this.cursoRepositorio = cursoRepositorio;
    }

    @Transactional
    public Topico crearTopico(TopicoRequest request) {
        validarDuplicado(request.getTitulo(), request.getMensaje());

        Usuario autor = usuarioRepositorio.findById(request.getAutorId())
                .orElseThrow(() -> new IllegalArgumentException("Autor no encontrado"));

        Curso curso = cursoRepositorio.findById(request.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        Topico topico = construirTopico(request, autor, curso);
        return topicoRepositorio.save(topico);
    }

    public Page<TopicoResponse> listarTopicos(Pageable pageable) {
        return topicoRepositorio.findAll(pageable)
                .map(this::mapearTopicoAResponse);
    }

    public TopicoResponse obtenerDetalle(Long id) {
        Topico topico = topicoRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tópico con ID " + id + " no encontrado"));

        return mapearTopicoAResponse(topico);
    }

    @Transactional
    public TopicoResponse actualizarTopico(Long id, TopicoUpdateRequest request) {
        Topico topico = topicoRepositorio.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tópico con ID " + id + " no encontrado"));

        validarDuplicado(request.getTitulo(), request.getMensaje());

        Curso curso = cursoRepositorio.findById(request.getCursoId())
                .orElseThrow(() -> new IllegalArgumentException("Curso no encontrado"));

        topico.setTitulo(request.getTitulo());
        topico.setMensaje(request.getMensaje());
        topico.setCurso(curso);

        return mapearTopicoAResponse(topico);
    }

    @Transactional
    public void eliminarTopico(Long id) {
        if (!topicoRepositorio.findById(id).isPresent()) {
            throw new NoSuchElementException("Tópico con ID " + id + " no encontrado");
        }

        topicoRepositorio.deleteById(id);
    }

    // 🔧 Métodos auxiliares

    private void validarDuplicado(String titulo, String mensaje) {
        if (topicoRepositorio.existsByTituloAndMensaje(titulo, mensaje)) {
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje");
        }
    }

    private Topico construirTopico(TopicoRequest request, Usuario autor, Curso curso) {
        Topico topico = new Topico();
        topico.setTitulo(request.getTitulo());
        topico.setMensaje(request.getMensaje());
        topico.setFechaCreacion(LocalDateTime.now());
        topico.setStatus(EstadoTopico.CREADO);
        topico.setAutor(autor);
        topico.setCurso(curso);
        return topico;
    }

    private TopicoResponse mapearTopicoAResponse(Topico topico) {
        return new TopicoResponse(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus().name(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}