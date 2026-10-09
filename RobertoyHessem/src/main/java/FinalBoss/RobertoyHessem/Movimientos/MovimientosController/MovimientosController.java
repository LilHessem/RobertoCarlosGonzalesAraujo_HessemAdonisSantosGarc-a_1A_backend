package FinalBoss.RobertoyHessem.Movimientos.MovimientosController;

import FinalBoss.RobertoyHessem.Cuentas.CuentasDTO.CuentasDTO;
import FinalBoss.RobertoyHessem.Cuentas.CuentasService.CuentasService;
import FinalBoss.RobertoyHessem.Cuentas.Response.ApiResponse;
import FinalBoss.RobertoyHessem.Movimientos.MovimientosDTO.MovimientosDTO;
import FinalBoss.RobertoyHessem.Movimientos.MovimientosService.MovimientosService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/movimientos")
public class MovimientosController {
    public MovimientosController(MovimientosService service) {
        this.service = service;
    }

    private final MovimientosService service;

    @PostMapping
    public ResponseEntity<ApiResponse<MovimientosDTO>> crear(@Valid @RequestBody MovimientosDTO json) {
        MovimientosDTO objDTO = service.insertarDatos(json);
        log.info("Nueva cuenta ingresada{}", objDTO);
        ApiResponse<MovimientosDTO> respuesta = new ApiResponse<>(true, "Dato ingresado exitosamente", objDTO);
        return ResponseEntity.ok(respuesta);
    }

    //@GetMapping("/{id}")
   // public ResponseEntity<ApiResponse<MovimientosDTO>> obtenerPorId(@PathVariable Integer id) {
    //    MovimientosDTO dto = new service.buscarPorId(id);
    //    return ResponseEntity.ok(new ApiResponse<>(true, "Dato encontrado", dto));
   // }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>>eliminar(@PathVariable Integer id) {
        service.eliminarPorId(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dato eliminado con exito", null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<MovimientosDTO>> actualizarInfo(@PathVariable Integer id, @Valid @RequestBody MovimientosDTO dto) {
        MovimientosDTO objDTO = service.actualizarInfo(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Proceso completado", objDTO));
    }

}
