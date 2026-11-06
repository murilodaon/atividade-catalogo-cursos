package br.edu.catalogocursos.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "instrutor")
public class Instrutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, unique = true, length = 160)
    private String email;

    @OneToMany(mappedBy = "instrutor", fetch = FetchType.LAZY)
    private List<Curso> cursos = new ArrayList<>();

    protected Instrutor() {}

    public Instrutor(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    public void adicionarCurso(Curso curso) {
        if (curso == null) throw new IllegalArgumentException("Curso não pode ser nulo");
        if (!cursos.contains(curso)) {
            curso.setInstrutor(this);
            cursos.add(curso);
        }
    }

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public List<Curso> getCursos() { return List.copyOf(cursos); }

    public void setId(Long id) { this.id = id; }
}
