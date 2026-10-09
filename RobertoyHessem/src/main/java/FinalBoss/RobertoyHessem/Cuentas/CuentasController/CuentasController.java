package FinalBoss.RobertoyHessem.Cuentas.CuentasController;

import FinalBoss.RobertoyHessem.Cuentas.CuentasDTO.CuentasDTO;
import FinalBoss.RobertoyHessem.Cuentas.CuentasService.CuentasService;
import FinalBoss.RobertoyHessem.Cuentas.Response.ApiResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cuentas")
public class CuentasController {

    public CuentasController(CuentasService service) {
        this.service = service;
    }

    private final CuentasService service;

    @PostMapping
    public ResponseEntity<ApiResponse<CuentasDTO>> crear(@Valid @RequestBody CuentasDTO json) {
        CuentasDTO objDTO = service.insertarDatos(json);
        log.info("Nueva cuenta ingresada{}", objDTO);
        ApiResponse<CuentasDTO> respuesta = new ApiResponse<>(true, "Dato ingresado exitosamente", objDTO);
        return ResponseEntity.ok(respuesta);
    }

    // @GetMapping("/{id}")
    //public ResponseEntity<ApiResponse<CuentasDTO>> obtenerPorId(@PathVariable Integer id) {
    //    CuentasDTO dto = new service.buscarPorId(id);
    //    return ResponseEntity.ok(new ApiResponse<>(true, "Dato encontrado", dto));
   // }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ApiResponse<List<CuentasDTO>>> obtenerPorNombre(@PathVariable String nombre) {
        List<CuentasDTO> lista = service.buscarPorNombre(nombre);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dato encontrado", lista));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>>eliminar(@PathVariable Integer id) {
        service.eliminarPorId(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dato eliminado con exito", null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CuentasDTO>> actualizarInfo(@PathVariable Integer id, @Valid @RequestBody CuentasDTO dto) {
        CuentasDTO objDTO = service.actualizarInfo(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Proceso completado", objDTO));
    }

}


