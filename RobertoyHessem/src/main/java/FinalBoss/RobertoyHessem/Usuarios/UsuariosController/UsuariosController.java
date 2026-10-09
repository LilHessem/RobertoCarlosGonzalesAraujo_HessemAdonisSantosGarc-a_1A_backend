package FinalBoss.RobertoyHessem.Usuarios.UsuariosController;

import FinalBoss.RobertoyHessem.Cuentas.CuentasDTO.CuentasDTO;
import FinalBoss.RobertoyHessem.Cuentas.CuentasService.CuentasService;
import FinalBoss.RobertoyHessem.Cuentas.Response.ApiResponse;
import FinalBoss.RobertoyHessem.Usuarios.UsuariosDTO.UsuariosDTO;
import FinalBoss.RobertoyHessem.Usuarios.UsuariosService.UsuariosService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {


    public UsuariosController(UsuariosService service) {
        this.service = service;
    }

    private final UsuariosService service;

    @PostMapping
    public ResponseEntity<ApiResponse<UsuariosDTO>> crear(@Valid @RequestBody UsuariosDTO json) {
        UsuariosDTO objDTO = service.insertarDatos(json);
        log.info("Nueva cuenta ingresada{}", objDTO);
        ApiResponse<UsuariosDTO> respuesta = new ApiResponse<>(true, "Dato ingresado exitosamente", objDTO);
        return ResponseEntity.ok(respuesta);
    }

   // @GetMapping("/{id}")
   // public ResponseEntity<ApiResponse<UsuariosDTO>> obtenerPorId(@PathVariable Integer id) {
    //    UsuariosDTO dto = new service.buscarPorId(id);
    //    return ResponseEntity.ok(new ApiResponse<>(true, "Dato encontrado", dto));
   // }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ApiResponse<List<UsuariosDTO>>> obtenerPorNombre(@PathVariable String nombre) {
        List<UsuariosDTO> lista = service.buscarPorNombre(nombre);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dato encontrado", lista));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>>eliminar(@PathVariable Integer id) {
        service.eliminarPorId(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dato eliminado con exito", null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UsuariosDTO>> actualizarInfo(@PathVariable Integer id, @Valid @RequestBody UsuariosDTO dto) {
        UsuariosDTO objDTO = service.actualizarInfo(id, dto);
        return ResponseEntity.ok(new ApiResponse<>(true, "Proceso completado", objDTO));
    }

}
