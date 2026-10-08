package pe.edu.utp.apf1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.apf1.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {}
