package FinalBoss.RobertoyHessem.Movimientos.MovimientosRepository;

import FinalBoss.RobertoyHessem.Movimientos.MovimientosEntity.MovimientosEntity;
import FinalBoss.RobertoyHessem.Usuarios.UsuariosEntity.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientosRepository extends JpaRepository<MovimientosEntity, Integer> {
}
