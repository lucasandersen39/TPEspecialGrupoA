package com.integrador.grupoA.swagger;

import com.integrador.grupoA.exceptions.model.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@ApiResponses({
        @ApiResponse(responseCode = "400", description = "Parámetros inválidos en la operación",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                        examples = @ExampleObject(name = "MontoNegativo", value = """
                {
                  "timestamp": "2025-06-22T13:10:00",
                  "codigo": "ERROR_VALIDACION",
                  "mensaje": "El monto a descontar debe ser un valor positivo"
                }
            """))),
        @ApiResponse(responseCode = "404", description = "Cuenta no encontrada",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                        examples = @ExampleObject(name = "CuentaNoExiste", value = """
                {
                  "timestamp": "2025-06-22T13:11:00",
                  "codigo": "RECURSO_NO_ENCONTRADO",
                  "mensaje": "No existe una cuenta con el id :42"
                }
            """))),
        @ApiResponse(responseCode = "409", description = "Conflicto en la operación de cuenta",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                        examples = {
                                @ExampleObject(name = "NumeroCuentaDuplicado", value = """
                    {
                      "timestamp": "2025-06-22T13:12:00",
                      "codigo": "ENTIDAD_DUPLICADA",
                      "mensaje": "Ya existe el número de cuenta :1234"
                    }
                """),
                                @ExampleObject(name = "SaldoInsuficiente", value = """
                    {
                      "timestamp": "2025-06-22T13:13:00",
                      "codigo": "ENTIDAD_DUPLICADA",
                      "mensaje": "Saldo insuficiente. Saldo actual: 20.00, Monto a descontar: 50.00"
                    }
                """)
                        }))
})
public interface SwaggerErrorResponsesCuentas {}


