package cl_duoc.ms_promociones.controller;

import cl_duoc.ms_promociones.model.Promocion;
import cl_duoc.ms_promociones.repository.PromocionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PromocionController.class)
public class PromocionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PromocionRepository promocionRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Promocion promocionMock;

    @BeforeEach
    void setUp() {
        promocionMock = new Promocion();
        promocionMock.setCodigo("VERANO20");
        promocionMock.setId(1L);


        promocionMock.setPorcentaje(20.0);


        promocionMock.setNombre("Descuento de verano");


        promocionMock.setEstado("ACTIVA");
    }

    @Test
    void crearPromocion_DebeRetornar201() throws Exception {

        when(promocionRepository.save(any(Promocion.class))).thenReturn(promocionMock);


        mockMvc.perform(post("/api/v1/promociones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(promocionMock)))
                .andExpect(status().isCreated());
    }

    @Test
    void buscarPorCodigo_Existe_DebeRetornar200() throws Exception {

        when(promocionRepository.findByCodigo("VERANO20")).thenReturn(promocionMock);

        mockMvc.perform(get("/api/v1/promociones/codigo/VERANO20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.codigo").value("VERANO20"));
    }
}