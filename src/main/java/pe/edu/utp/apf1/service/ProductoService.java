package pe.edu.utp.apf1.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.utp.apf1.dto.*;
import pe.edu.utp.apf1.exception.RecursoNoEncontradoException;
import pe.edu.utp.apf1.model.Producto;
import pe.edu.utp.apf1.repository.ProductoRepository;
import java.util.List;

@Service
public class ProductoService {
    private final ProductoRepository repository;
    public ProductoService(ProductoRepository repository){this.repository=repository;}
    @Transactional(readOnly=true)
    public List<ProductoResponse> listar(){return repository.findAll().stream().map(this::respuesta).toList();}
    @Transactional(readOnly=true)
    public ProductoResponse obtener(Long id){return respuesta(buscar(id));}
    @Transactional
    public ProductoResponse crear(ProductoRequest request){return respuesta(repository.save(nuevo(request)));}
    @Transactional
    public ProductoResponse actualizar(Long id, ProductoRequest request){
        Producto p=buscar(id); p.setNombre(request.nombre().trim()); p.setPrecio(request.precio()); p.setStock(request.stock());
        return respuesta(repository.save(p));
    }
    @Transactional
    public void eliminar(Long id){repository.delete(buscar(id));}
    private Producto buscar(Long id){return repository.findById(id).orElseThrow(()->new RecursoNoEncontradoException("Producto no encontrado con id "+id));}
    private Producto nuevo(ProductoRequest r){return new Producto(null,r.nombre().trim(),r.precio(),r.stock());}
    private ProductoResponse respuesta(Producto p){return new ProductoResponse(p.getId(),p.getNombre(),p.getPrecio(),p.getStock());}
}
