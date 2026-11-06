package br.edu.catalogocursos.application;

import br.edu.catalogocursos.api.dto.*;
import br.edu.catalogocursos.api.mapper.InstrutorMapper;
import br.edu.catalogocursos.api.mapper.CursoMapper;
import br.edu.catalogocursos.domain.Instrutor;
import br.edu.catalogocursos.repository.InstrutorRepository;
import br.edu.catalogocursos.repository.CursoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstrutorService {
    private final InstrutorRepository instrutorRepository;
    private final CursoRepository cursoRepository;
    private final InstrutorMapper instrutorMapper;
    private final CursoMapper cursoMapper;

    public InstrutorService(InstrutorRepository instrutorRepository, CursoRepository cursoRepository,
                            InstrutorMapper instrutorMapper, CursoMapper cursoMapper) {
        this.instrutorRepository = instrutorRepository;
        this.cursoRepository = cursoRepository;
        this.instrutorMapper = instrutorMapper;
        this.cursoMapper = cursoMapper;
    }

    @Transactional
    public InstrutorResponse criar(InstrutorRequest request) {
        if (instrutorRepository.existsByEmail(request.email()))
            throw new EmailDuplicadoException("Email já cadastrado");
        Instrutor instrutor = instrutorRepository.save(new Instrutor(request.nome(), request.email()));
        return instrutorMapper.toResponse(instrutor);
    }

    @Transactional(readOnly = true)
    public InstrutorResponse buscar(Long id) {
        Instrutor instrutor = instrutorRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Instrutor não encontrado"));
        return instrutorMapper.toResponse(instrutor);
    }

    @Transactional(readOnly = true)
    public List<CursoResumoResponse> listarCursos(Long instrutorId) {
        if (!instrutorRepository.existsById(instrutorId))
            throw new RecursoNaoEncontradoException("Instrutor não encontrado");
        return cursoRepository.findByInstrutorIdOrderByTituloAsc(instrutorId)
                .stream().map(cursoMapper::toResumo).toList();
    }
}
