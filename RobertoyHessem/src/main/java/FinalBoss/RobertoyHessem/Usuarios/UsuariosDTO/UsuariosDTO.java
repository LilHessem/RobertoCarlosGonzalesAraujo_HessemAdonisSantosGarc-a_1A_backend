package FinalBoss.RobertoyHessem.Usuarios.UsuariosDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class UsuariosDTO {
    private Integer idUsuario;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    @Size(max = 150, message = "Maximo de caracteres 150")
    private String correo;

    @NotBlank(message = "Saldo inicial obligatorio")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaRegistro;

}
