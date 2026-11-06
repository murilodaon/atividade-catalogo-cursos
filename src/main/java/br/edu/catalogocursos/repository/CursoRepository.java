package br.edu.catalogocursos.repository;

import br.edu.catalogocursos.domain.Curso;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    List<Curso> findByInstrutorIdOrderByTituloAsc(Long instrutorId);
}
