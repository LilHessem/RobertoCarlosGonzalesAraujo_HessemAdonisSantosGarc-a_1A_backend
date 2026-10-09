package FinalBoss.RobertoyHessem.Usuarios.UsuariosRepository;

import FinalBoss.RobertoyHessem.Cuentas.CuentasEntity.CuentasEntity;
import FinalBoss.RobertoyHessem.Usuarios.UsuariosEntity.UsuariosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuariosRepository extends JpaRepository<UsuariosEntity, Integer>{
    List<UsuariosEntity> findByNombreusuario(String nombreusuario);
}
