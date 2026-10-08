package pe.edu.utp.apf1.controller;

import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import pe.edu.utp.apf1.dto.*;
import pe.edu.utp.apf1.service.ProductoService;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {
    private final ProductoService service;
    public ProductoController(ProductoService service){this.service=service;}
    @GetMapping public ResponseEntity<List<ProductoResponse>> listar(){return ResponseEntity.ok(service.listar());}
    @GetMapping("/{id}") public ResponseEntity<ProductoResponse> obtener(@PathVariable Long id){return ResponseEntity.ok(service.obtener(id));}
    @PostMapping public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest request){
        ProductoResponse creado=service.crear(request);
        return ResponseEntity.created(URI.create("/api/v1/productos/"+creado.id())).body(creado);
    }
    @PutMapping("/{id}") public ResponseEntity<ProductoResponse> actualizar(@PathVariable Long id,@Valid @RequestBody ProductoRequest request){return ResponseEntity.ok(service.actualizar(id,request));}
    @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Long id){service.eliminar(id); return ResponseEntity.noContent().build();}
}
