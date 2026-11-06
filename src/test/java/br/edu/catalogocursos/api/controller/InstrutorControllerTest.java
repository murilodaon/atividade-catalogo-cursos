package br.edu.catalogocursos.api.controller;

import br.edu.catalogocursos.api.dto.InstrutorRequest;
import br.edu.catalogocursos.api.exception.GlobalExceptionHandler;
import br.edu.catalogocursos.application.InstrutorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InstrutorController.class)
@Import(GlobalExceptionHandler.class)
class InstrutorControllerTest {
    @Autowired MockMvc mvc;
    @MockBean InstrutorService service;

    @Test
    void instrutorInvalidoRetorna400() throws Exception {
        mvc.perform(post("/api/instrutores")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\":\"Jo\",\"email\":\"email-invalido\"}"))
                .andExpect(status().isBadRequest());
        verifyNoInteractions(service);
    }
}
