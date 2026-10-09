package FinalBoss.RobertoyHessem.Movimientos.MovimientosService;

import FinalBoss.RobertoyHessem.Movimientos.MovimientosDTO.MovimientosDTO;
import FinalBoss.RobertoyHessem.Movimientos.MovimientosEntity.MovimientosEntity;
import FinalBoss.RobertoyHessem.Movimientos.MovimientosRepository.MovimientosRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class MovimientosService {

    public MovimientosService(MovimientosRepository repo) {
        this.repo = repo;
    }

    private final MovimientosRepository repo;

    @Transactional
    public MovimientosDTO insertarDatos(@Valid MovimientosDTO jsonData){
        if (jsonData == null){
            throw new IllegalArgumentException("El dato no puede ser nulo");
        }
        MovimientosEntity entity = convertirAEntity(jsonData);
        MovimientosEntity entitySave = repo.save(entity);
        return convertirADTO(entitySave);
    }

    public MovimientosDTO convertirADTO(MovimientosEntity entitySave){
        if (entitySave == null)throw new IllegalArgumentException("Error al guardar la entidad, el resultado es nulo");
        MovimientosDTO objDTO = new MovimientosDTO();
        objDTO.setIdMovimiento(entitySave.getIdMovimiento());
        objDTO.setId_cuenta(entitySave.getIdCuenta());
        objDTO.setCategoria(entitySave.getCategoria());
        objDTO.setTipo(entitySave.getTipo());
        objDTO.setDescripcion(entitySave.getDescripcion());
        objDTO.setMonto(entitySave.getMonto());
        objDTO.setFecha(entitySave.getFecha());
        return objDTO;
    }

    public MovimientosEntity convertirAEntity(@Valid MovimientosDTO jsonData){
        if (jsonData == null) throw new IllegalArgumentException("El dato  proporcionado no puede ser nulo");
        MovimientosEntity objEntity = new MovimientosEntity();
        objEntity.setIdCuenta(jsonData.getId_cuenta());
        objEntity.setTipo(jsonData.getTipo());
        objEntity.setFecha(jsonData.getFecha());
        objEntity.setCategoria(jsonData.getCategoria());
        objEntity.setDescripcion(jsonData.getDescripcion());
        objEntity.setMonto(jsonData.getMonto());
        return objEntity;
    }

    public List<MovimientosDTO> listarTodos(){
        List<MovimientosEntity>entidades = repo.findAll();
        return entidades.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public MovimientosDTO buscarPorId(Integer id){
        return repo.findById(id)
                .map(this::convertirADTO)
                .orElseThrow(()-> new IllegalStateException("No se encontro el movimiento"));
    }

    @Transactional
    public boolean eliminarPorId(Integer id){
        if (!repo.existsById(id)){
            throw new IllegalStateException("Movimiento no encontrado");
        }
        repo.deleteById(id);
        return true;
    }

    @Transactional
    public MovimientosDTO actualizarInfo(Integer id, @Valid MovimientosDTO dto){
        MovimientosEntity entidad = repo.findById(id).orElseThrow(()-> new IllegalStateException("Movimiento no encontrado"));
        entidad.setIdCuenta(dto.getId_cuenta());
        entidad.setTipo(dto.getTipo());
        entidad.setFecha(dto.getFecha());
        entidad.setCategoria(dto.getCategoria());
        entidad.setMonto(dto.getMonto());
        entidad.setDescripcion(dto.getDescripcion());
        MovimientosEntity datosGuardados = repo.save(entidad);
        return convertirADTO(datosGuardados);
    }
}
