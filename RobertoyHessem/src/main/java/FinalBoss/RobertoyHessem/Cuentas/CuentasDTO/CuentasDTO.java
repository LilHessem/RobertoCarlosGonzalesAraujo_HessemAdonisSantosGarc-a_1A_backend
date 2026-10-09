package FinalBoss.RobertoyHessem.Cuentas.CuentasDTO;


import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CuentasDTO {
    private Integer idCuenta;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @NotBlank(message = "Usuario obligatorio")
    private Integer id_usuario;

    @NotBlank(message = "Tipo de usuario obligatorio")
    @Size(max = 20, message = "Maximo de caracteres 20")
    private String tipo;

    @NotBlank(message = "Saldo inicial obligatorio")
    private double saldoInicial;

    @NotBlank()
    private char activa;
}
