package br.edu.catalogocursos.api.controller;

import br.edu.catalogocursos.api.dto.*;
import br.edu.catalogocursos.application.InstrutorService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/instrutores")
public class InstrutorController {
    private final InstrutorService service;

    public InstrutorController(InstrutorService service) { this.service = service; }

    @PostMapping
    public ResponseEntity<InstrutorResponse> criar(@Valid @RequestBody InstrutorRequest request) {
        InstrutorResponse response = service.criar(request);
        return ResponseEntity.created(URI.create("/api/instrutores/" + response.id())).body(response);
    }

    @GetMapping("/{id}/cursos")
    public ResponseEntity<List<CursoResumoResponse>> listarCursos(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarCursos(id));
    }
}
