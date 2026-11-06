package br.edu.catalogocursos.application;

import br.edu.catalogocursos.api.dto.CursoResumoResponse;
import br.edu.catalogocursos.api.mapper.CursoMapper;
import br.edu.catalogocursos.api.mapper.InstrutorMapper;
import br.edu.catalogocursos.domain.Curso;
import br.edu.catalogocursos.domain.Instrutor;
import br.edu.catalogocursos.domain.NivelCurso;
import br.edu.catalogocursos.repository.CursoRepository;
import br.edu.catalogocursos.repository.InstrutorRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InstrutorServiceTest {
    @Test
    void listaSomenteCursosDoInstrutorEmOrdemDeTitulo() {
        InstrutorRepository instrutores = mock(InstrutorRepository.class);
        CursoRepository cursos = mock(CursoRepository.class);
        when(instrutores.existsById(1L)).thenReturn(true);
        Curso cursoA = new Curso("APIs REST", 40, NivelCurso.INTERMEDIARIO);
        cursoA.setId(2L);
        Curso cursoB = new Curso("Java", 60, NivelCurso.INICIANTE);
        cursoB.setId(3L);
        when(cursos.findByInstrutorIdOrderByTituloAsc(1L)).thenReturn(List.of(cursoA, cursoB));

        InstrutorService service = new InstrutorService(instrutores, cursos, new InstrutorMapper(), new CursoMapper());
        List<CursoResumoResponse> resultado = service.listarCursos(1L);

        assertEquals(List.of("APIs REST", "Java"), resultado.stream().map(CursoResumoResponse::titulo).toList());
        verify(cursos).findByInstrutorIdOrderByTituloAsc(1L);
    }
}
