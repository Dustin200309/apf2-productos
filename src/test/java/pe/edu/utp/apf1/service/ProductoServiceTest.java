package pe.edu.utp.apf1.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.utp.apf1.dto.*;
import pe.edu.utp.apf1.model.Producto;
import pe.edu.utp.apf1.repository.ProductoRepository;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {
    @Mock ProductoRepository repository;
    @InjectMocks ProductoService service;
    @Test void crearProductoDebeRetornarProductoGuardado(){
        ProductoRequest request=new ProductoRequest("Teclado",new BigDecimal("150.00"),10);
        when(repository.save(any(Producto.class))).thenReturn(new Producto(1L,"Teclado",new BigDecimal("150.00"),10));
        ProductoResponse respuesta=service.crear(request);
        assertEquals(1L,respuesta.id()); assertEquals("Teclado",respuesta.nombre()); verify(repository).save(any(Producto.class));
    }
}
