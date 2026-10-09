package FinalBoss.RobertoyHessem.Movimientos.MovimientosDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
public class MovimientosDTO {
    private Integer idMovimiento;

    @NotBlank(message = "El tipo de movimiento es obligatorio")
    @Size(max = 10, message = "Maximo de caracteres del tipo: 10")
    private String tipo;

    @NotBlank(message = "La categoria de movimiento es obligatorio")
    @Size(max = 10, message = "Maximo de caracteres es 80")
    private String categoria;

    @Size(max = 300, message = "Maximo de caracteres es 300")
    private String descripcion;

    @NotBlank(message = "Usuario obligatorio")
    private Integer id_cuenta;

    @NotBlank(message = "Fecha obligatoria")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fecha;

    @NotBlank(message = "No puedes poner un monto nulo")
    private double monto;

}
