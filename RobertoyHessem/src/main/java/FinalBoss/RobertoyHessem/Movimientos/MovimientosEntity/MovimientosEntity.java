package FinalBoss.RobertoyHessem.Movimientos.MovimientosEntity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.util.Date;

@Data
@Entity
@Table(name = "movimientos")
public class MovimientosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimiento")
    private Integer idMovimiento;

    @Column(name = "id_cuenta", nullable = false)
    private Integer idCuenta;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "tipo", nullable = false, length = 10)
    private String tipo;

    @Column(name = "categoria", nullable = false, length = 80)
    private String categoria;

    @Column(name = "descripcion", length = 300)
    private String descripcion;

    @Column(name = "monto", nullable = false)
    private double monto;
}
