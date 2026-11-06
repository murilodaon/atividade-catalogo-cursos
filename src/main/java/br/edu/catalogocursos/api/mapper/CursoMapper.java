package br.edu.catalogocursos.api.mapper;

import br.edu.catalogocursos.api.dto.*;
import br.edu.catalogocursos.domain.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {
    public CursoResponse toResponse(Curso curso) {
        return new CursoResponse(curso.getId(), curso.getTitulo(), curso.getCargaHoraria(),
                curso.getNivel().name(),
                new InstrutorResumoResponse(curso.getInstrutor().getId(), curso.getInstrutor().getNome()));
    }

    public CursoResumoResponse toResumo(Curso curso) {
        return new CursoResumoResponse(curso.getId(), curso.getTitulo(),
                curso.getCargaHoraria(), curso.getNivel().name());
    }
}
