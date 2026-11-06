package br.edu.catalogocursos.application;

import br.edu.catalogocursos.api.dto.CursoRequest;
import br.edu.catalogocursos.api.dto.CursoResponse;
import br.edu.catalogocursos.api.mapper.CursoMapper;
import br.edu.catalogocursos.domain.Curso;
import br.edu.catalogocursos.domain.Instrutor;
import br.edu.catalogocursos.repository.CursoRepository;
import br.edu.catalogocursos.repository.InstrutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;
    private final InstrutorRepository instrutorRepository;
    private final CursoMapper cursoMapper;

    public CursoService(CursoRepository cursoRepository, InstrutorRepository instrutorRepository,
                        CursoMapper cursoMapper) {
        this.cursoRepository = cursoRepository;
        this.instrutorRepository = instrutorRepository;
        this.cursoMapper = cursoMapper;
    }

    @Transactional
    public CursoResponse criar(CursoRequest request) {
        Instrutor instrutor = instrutorRepository.findById(request.instrutorId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Instrutor não encontrado"));
        Curso curso = new Curso(request.titulo(), request.cargaHoraria(), request.nivel());
        instrutor.adicionarCurso(curso);
        Curso salvo = cursoRepository.save(curso);
        return cursoMapper.toResponse(salvo);
    }

    @Transactional(readOnly = true)
    public CursoResponse buscar(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Curso não encontrado"));
        return cursoMapper.toResponse(curso);
    }
}
