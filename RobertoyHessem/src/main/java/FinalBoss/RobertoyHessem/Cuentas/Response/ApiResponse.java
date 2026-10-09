package FinalBoss.RobertoyHessem.Cuentas.Response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ApiResponse<T> {

    public ApiResponse(boolean succes, String message) {
        this.succes = succes;
        this.message = message;
    }

    private boolean succes;

    public ApiResponse(boolean succes, String message, T data) {
        this.succes = succes;
        this.message = message;
        this.data = data;
    }

    private String message;
    private T data;
}
