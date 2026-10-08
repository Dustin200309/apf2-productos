package pe.edu.utp.apf1.security;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pe.edu.utp.apf1.repository.UsuarioRepository;
@Configuration
public class SecurityConfig {
 @Bean PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}
 @Bean UserDetailsService userDetailsService(UsuarioRepository repo){return username -> repo.findByUsername(username).map(u -> User.withUsername(u.getUsername()).password(u.getPassword()).roles(u.getRol()).build()).orElseThrow(()->new UsernameNotFoundException("Usuario no encontrado"));}
 @Bean SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
  return http.csrf(csrf->csrf.disable()).sessionManagement(s->s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
   .authorizeHttpRequests(a->a.requestMatchers("/error").permitAll()
    .requestMatchers(HttpMethod.GET,"/api/v1/productos","/api/v1/productos/**").hasAnyRole("USER","ADMIN")
    .requestMatchers("/api/v1/productos","/api/v1/productos/**").hasRole("ADMIN")
    .requestMatchers("/swagger-ui/**","/swagger-ui.html","/v3/api-docs/**").hasRole("ADMIN")
    .anyRequest().authenticated()).httpBasic(Customizer.withDefaults()).build();
 }
}
