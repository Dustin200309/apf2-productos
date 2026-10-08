package pe.edu.utp.apf1.security;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import pe.edu.utp.apf1.model.Usuario;
import pe.edu.utp.apf1.repository.UsuarioRepository;
@Configuration
public class UsuariosIniciales {
 @Bean CommandLineRunner crearUsuarios(UsuarioRepository repo, PasswordEncoder encoder){return args->{
  crearSiFalta(repo,encoder,"admin","APF2_ADMIN_PASSWORD","ADMIN");
  crearSiFalta(repo,encoder,"usuario","APF2_USER_PASSWORD","USER");
 };}
 private void crearSiFalta(UsuarioRepository repo,PasswordEncoder encoder,String username,String variable,String rol){
  String clave=System.getenv(variable);
  if(clave!=null && clave.length()>=12 && repo.findByUsername(username).isEmpty()) repo.save(new Usuario(username,encoder.encode(clave),rol));
 }
}
