package FinalBoss.RobertoyHessem.Usuarios.UsuariosService;

import FinalBoss.RobertoyHessem.RobertoyHessemApplication;
import FinalBoss.RobertoyHessem.Usuarios.UsuariosDTO.UsuariosDTO;
import FinalBoss.RobertoyHessem.Usuarios.UsuariosEntity.UsuariosEntity;
import FinalBoss.RobertoyHessem.Usuarios.UsuariosRepository.UsuariosRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class UsuariosService {

    private final UsuariosRepository repo;

    public UsuariosService(UsuariosRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public UsuariosDTO insertarDatos(@Valid UsuariosDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        UsuariosEntity entity = convertirAEntity(jsonData);
        UsuariosEntity entitySave = repo.save(entity);
        return convertirADTO(entitySave);
    }

    public UsuariosDTO convertirADTO(UsuariosEntity entitySave){
        if (entitySave == null)throw new IllegalStateException("Error al guardar la entidad, el resultado es nulo");
        UsuariosDTO objDTO = new UsuariosDTO();
        objDTO.setIdUsuario(entitySave.getIdUsuario());
        objDTO.setNombre(entitySave.getNombre());
        objDTO.setCorreo(entitySave.getCorreo());
        objDTO.setFechaRegistro(entitySave.getFecha_registro());
        return objDTO;
    }

    public UsuariosEntity convertirAEntity(@Valid UsuariosDTO jsonData){
        if (jsonData == null) throw new IllegalArgumentException("El dato  proporcionado no puede ser nulo");
        UsuariosEntity objEntity = new UsuariosEntity();
        objEntity.setNombre(jsonData.getNombre());
        objEntity.setCorreo(jsonData.getCorreo());
        objEntity.setFecha_registro(jsonData.getFechaRegistro());
        return objEntity;
    }

    public List<UsuariosDTO>listarTodos(){
        List<UsuariosEntity>entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public UsuariosDTO buscarPorId(Integer id){
        return repo.findById(id)
                .map(this::convertirADTO)
                .orElseThrow(()-> new IllegalStateException("No se encontraron los Usuarios de id: " + id ));
    }

    public List<UsuariosDTO>buscarPorNombre(String nombre){
        List<UsuariosEntity> entidades = repo.findByNombreusuario(nombre);
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
    public UsuariosDTO actualizarInfo(Integer id, @Valid UsuariosDTO dto){
        UsuariosEntity entidad = repo.findById(id).orElseThrow(()-> new IllegalStateException("Usuario no encontrado"));
        entidad.setNombre(dto.getNombre());
        entidad.setFecha_registro(dto.getFechaRegistro());
        entidad.setCorreo(dto.getCorreo());
        UsuariosEntity datosGuardados = repo.save(entidad);
        return convertirADTO(datosGuardados);
    }
}
