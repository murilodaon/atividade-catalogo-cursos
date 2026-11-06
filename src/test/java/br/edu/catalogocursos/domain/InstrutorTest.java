package br.edu.catalogocursos.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class InstrutorTest {
    @Test
    void associaCursoNosDoisLadosSemDuplicar() {
        Instrutor instrutor = new Instrutor("Marina Costa", "marina@example.com");
        Curso curso = new Curso("Java básico", 40, NivelCurso.INICIANTE);

        instrutor.adicionarCurso(curso);
        instrutor.adicionarCurso(curso);

        assertEquals(1, instrutor.getCursos().size());
        assertSame(instrutor, curso.getInstrutor());
    }
}
