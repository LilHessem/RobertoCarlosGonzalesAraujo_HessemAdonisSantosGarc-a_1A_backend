package FinalBoss.RobertoyHessem.Cuentas.CuentasRepository;

import FinalBoss.RobertoyHessem.Cuentas.CuentasEntity.CuentasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentasRepository extends JpaRepository<CuentasEntity, Integer> {
    List<CuentasEntity>findByNombrecuenta(String nombrecuenta);
}
