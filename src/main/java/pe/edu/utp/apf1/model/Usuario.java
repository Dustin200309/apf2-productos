package pe.edu.utp.apf1.model;
import jakarta.persistence.*;
@Entity @Table(name="usuarios")
public class Usuario {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=80) private String username;
 @Column(nullable=false) private String password;
 @Column(nullable=false,length=20) private String rol;
 public Usuario() {} public Usuario(String username,String password,String rol){this.username=username;this.password=password;this.rol=rol;}
 public Long getId(){return id;} public String getUsername(){return username;} public String getPassword(){return password;} public String getRol(){return rol;}
}
