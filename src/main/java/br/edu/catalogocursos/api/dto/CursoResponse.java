package br.edu.catalogocursos.api.dto;

public record CursoResponse(Long id, String titulo, Integer cargaHoraria, String nivel,
                            InstrutorResumoResponse instrutor) {}
