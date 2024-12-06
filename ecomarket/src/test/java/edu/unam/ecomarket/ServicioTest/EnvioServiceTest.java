package edu.unam.ecomarket.ServicioTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import edu.unam.ecomarket.modelo.MetodoEnvio;
import edu.unam.ecomarket.repositories.EnvioRepository;
import edu.unam.ecomarket.services.EnvioService;

class EnvioServiceTest {

    @Mock
    private EnvioRepository envioRepository;

    @InjectMocks
    private EnvioService envioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCrearEnvio() {
        // Crear un mock de MetodoEnvio
        MetodoEnvio metodoEnvioMock = mock(MetodoEnvio.class);

        // Configurar el repositorio para devolver el mock cuando se guarde
        when(envioRepository.save(metodoEnvioMock)).thenReturn(metodoEnvioMock);

        // Llamar al método del servicio
        MetodoEnvio resultado = envioService.crearEnvio(metodoEnvioMock);

        // Verificar el resultado
        assertNotNull(resultado);
        verify(envioRepository, times(1)).save(metodoEnvioMock);
    }
}
