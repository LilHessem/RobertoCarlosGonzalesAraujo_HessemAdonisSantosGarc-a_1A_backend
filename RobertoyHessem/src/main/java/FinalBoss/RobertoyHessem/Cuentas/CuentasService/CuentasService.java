package FinalBoss.RobertoyHessem.Cuentas.CuentasService;

import FinalBoss.RobertoyHessem.Cuentas.CuentasDTO.CuentasDTO;
import FinalBoss.RobertoyHessem.Cuentas.CuentasEntity.CuentasEntity;
import FinalBoss.RobertoyHessem.Cuentas.CuentasRepository.CuentasRepository;
import FinalBoss.RobertoyHessem.Usuarios.UsuariosDTO.UsuariosDTO;
import FinalBoss.RobertoyHessem.Usuarios.UsuariosEntity.UsuariosEntity;
import FinalBoss.RobertoyHessem.Usuarios.UsuariosRepository.UsuariosRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class CuentasService {

    public CuentasService(CuentasRepository repo) {
        this.repo = repo;
    }

    private final CuentasRepository repo;


    @Transactional
    public CuentasDTO insertarDatos(@Valid CuentasDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        CuentasEntity entity = convertirAEntity(jsonData);
        CuentasEntity entitySave = repo.save(entity);
        return convertirADTO(entitySave);
    }

    public CuentasDTO convertirADTO(CuentasEntity entitySave){
        if (entitySave == null)throw new IllegalStateException("Error al guardar la entidad, el resultado es nulo");
        CuentasDTO objDTO = new CuentasDTO();
        objDTO.setIdCuenta(entitySave.getIdCuenta());
        objDTO.setNombre(entitySave.getNombre());
        objDTO.setTipo(entitySave.getTipo());
        objDTO.setSaldoInicial(entitySave.getSaldoInicial());
        objDTO.setActiva(entitySave.getActiva());
        return objDTO;
    }

    public CuentasEntity convertirAEntity(@Valid CuentasDTO jsonData){
        if (jsonData == null) throw new IllegalArgumentException("El dato  proporcionado no puede ser nulo");
        CuentasEntity objEntity = new CuentasEntity();
        objEntity.setIdCuenta(jsonData.getIdCuenta());
        objEntity.setNombre(jsonData.getNombre());
        objEntity.setTipo(jsonData.getTipo());
        objEntity.setIdUsuario(jsonData.getId_usuario());
        objEntity.setSaldoInicial(jsonData.getSaldoInicial());
        objEntity.setActiva(jsonData.getActiva());
        return objEntity;
    }

    public List<CuentasDTO> listarTodos(){
        List<CuentasEntity>entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public CuentasDTO buscarPorId(Integer id){
        return repo.findById(id)
                .map(this::convertirADTO)
                .orElseThrow(()-> new IllegalStateException("No se encontraron los Usuarios de id: " + id ));
    }

    public List<CuentasDTO>buscarPorNombre(String nombre){
        List<CuentasEntity> entidades = repo.findByNombrecuenta(nombre);
        if (entidades.isEmpty()){
            throw new IllegalStateException("No se encontraron los usuarios de nombre: " + nombre);
        }
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean eliminarPorId(Integer id){
        if (!repo.existsById(id)){
            throw new IllegalStateException("Usuario no encontrado");
        }
        repo.deleteById(id);
        return true;
    }

    @Transactional
    public CuentasDTO actualizarInfo(Integer id, @Valid CuentasDTO dto){
        CuentasEntity entidad = repo.findById(id).orElseThrow(()-> new IllegalStateException("Usuario no encontrado"));
        entidad.setNombre(dto.getNombre());
        entidad.setTipo(dto.getTipo());
        entidad.setIdUsuario(dto.getId_usuario());
        entidad.setActiva(dto.getActiva());
        entidad.setSaldoInicial(dto.getSaldoInicial());
        CuentasEntity datosGuardados = repo.save(entidad);
        return convertirADTO(datosGuardados);
    }
}
