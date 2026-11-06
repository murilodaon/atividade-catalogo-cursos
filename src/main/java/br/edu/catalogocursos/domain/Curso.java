package br.edu.catalogocursos.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String titulo;

    @Column(name = "carga_horaria", nullable = false)
    private Integer cargaHoraria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private NivelCurso nivel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "instrutor_id", nullable = false)
    private Instrutor instrutor;

    protected Curso() {}

    public Curso(String titulo, Integer cargaHoraria, NivelCurso nivel) {
        this.titulo = titulo;
        this.cargaHoraria = cargaHoraria;
        this.nivel = nivel;
    }

    void setInstrutor(Instrutor instrutor) { this.instrutor = instrutor; }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public Integer getCargaHoraria() { return cargaHoraria; }
    public NivelCurso getNivel() { return nivel; }
    public Instrutor getInstrutor() { return instrutor; }
    public void setId(Long id) { this.id = id; }
}
