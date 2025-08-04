package com.forohub.repositorio;

import com.forohub.modelo.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepositorio extends JpaRepository<Topico, Long> {

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

    // 🔍 Filtro por nombre de curso y año de creación
    @Query("SELECT t FROM Topico t WHERE t.curso.nombre = :nombreCurso AND FUNCTION('YEAR', t.fechaCreacion) = :anio")
    Page<Topico> buscarPorCursoYAnio(@Param("nombreCurso") String nombreCurso,
                                     @Param("anio") int anio,
                                     Pageable pageable);
}