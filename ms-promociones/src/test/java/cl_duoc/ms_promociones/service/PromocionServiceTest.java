package cl_duoc.ms_promociones.service;

import cl_duoc.ms_promociones.model.Promocion;
import cl_duoc.ms_promociones.repository.PromocionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PromocionServiceTest {

    @Mock
    private PromocionRepository promocionRepository;

    @InjectMocks
    private PromocionService promocionService;

    private Promocion promocionMock;

    @BeforeEach
    void setUp() {
        promocionMock = new Promocion();
        promocionMock.setCodigo("INVIERNO15");
    }

    @Test
    void findAll_DebeRetornarLista() {
        when(promocionRepository.findAll()).thenReturn(Arrays.asList(promocionMock));
        List<Promocion> resultado = promocionService.findAll();
        assertFalse(resultado.isEmpty());
        verify(promocionRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_Existe_RetornaPromocion() {
        when(promocionRepository.findById(1L)).thenReturn(Optional.of(promocionMock));
        Promocion resultado = promocionService.buscarPorId(1L);
        assertNotNull(resultado);
    }
}