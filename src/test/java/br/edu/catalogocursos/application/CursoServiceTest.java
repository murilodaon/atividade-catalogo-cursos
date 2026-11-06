package br.edu.catalogocursos.application;

import br.edu.catalogocursos.api.dto.CursoRequest;
import br.edu.catalogocursos.api.mapper.CursoMapper;
import br.edu.catalogocursos.domain.NivelCurso;
import br.edu.catalogocursos.repository.CursoRepository;
import br.edu.catalogocursos.repository.InstrutorRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CursoServiceTest {
    @Test
    void rejeitaCursoQuandoInstrutorNaoExiste() {
        CursoRepository cursos = mock(CursoRepository.class);
        InstrutorRepository instrutores = mock(InstrutorRepository.class);
        CursoService service = new CursoService(cursos, instrutores, new CursoMapper());
        when(instrutores.findById(99L)).thenReturn(Optional.empty());

        CursoRequest request = new CursoRequest("Java básico", 40, NivelCurso.INICIANTE, 99L);
        assertThrows(RecursoNaoEncontradoException.class, () -> service.criar(request));
        verify(cursos, never()).save(any());
    }
}
