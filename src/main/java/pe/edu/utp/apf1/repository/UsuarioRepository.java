package pe.edu.utp.apf1.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.utp.apf1.model.Usuario;
public interface UsuarioRepository extends JpaRepository<Usuario,Long>{Optional<Usuario> findByUsername(String username);}
