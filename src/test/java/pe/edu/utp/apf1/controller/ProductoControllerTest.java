package pe.edu.utp.apf1.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import pe.edu.utp.apf1.dto.ProductoResponse;
import pe.edu.utp.apf1.exception.GlobalExceptionHandler;
import pe.edu.utp.apf1.service.ProductoService;
import java.math.BigDecimal;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest({ProductoController.class, GlobalExceptionHandler.class})
@AutoConfigureMockMvc(addFilters=false)
class ProductoControllerTest {
    @Autowired MockMvc mockMvc;
    @MockitoBean ProductoService service;
    @Test void crearProductoValidoDebeResponder201() throws Exception {
        when(service.crear(any())).thenReturn(new ProductoResponse(1L,"Mouse",new BigDecimal("85.00"),8));
        mockMvc.perform(post("/api/v1/productos").contentType(MediaType.APPLICATION_JSON).content("{\"nombre\":\"Mouse\",\"precio\":85,\"stock\":8}"))
            .andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(1));
    }
    @Test void crearProductoInvalidoDebeResponder400() throws Exception {
        mockMvc.perform(post("/api/v1/productos").contentType(MediaType.APPLICATION_JSON).content("{\"nombre\":\"\",\"precio\":0,\"stock\":-1}"))
            .andExpect(status().isBadRequest()).andExpect(jsonPath("$.mensaje").value("Datos inválidos"));
    }
}
