package com.integrador.grupoA.swagger;

import com.integrador.grupoA.exceptions.model.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@ApiResponses({
        @ApiResponse(responseCode = "400", description = "Error de validación en tipo de tarifa",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                        examples = @ExampleObject(name = "ValidaciónTipoTarifa", value = """
                {
                  "timestamp": "2025-06-22T10:20:00",
                  "codigo": "ERROR_VALIDACION",
                  "mensaje": "El nombre del tipo de tarifa no puede estar vacío"
                }
            """))),
        @ApiResponse(responseCode = "404", description = "Tipo de tarifa no encontrado",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                        examples = @ExampleObject(name = "TipoTarifaNoEncontrado", value = """
                {
                  "timestamp": "2025-06-22T10:21:00",
                  "codigo": "RECURSO_NO_ENCONTRADO",
                  "mensaje": "Tipo de tarifa no encontrado con el id :3"
                }
            """))),
        @ApiResponse(responseCode = "409", description = "No se puede eliminar: tarifas asociadas",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                        examples = @ExampleObject(name = "TarifasAsociadas", value = """
                {
                  "timestamp": "2025-06-22T10:22:00",
                  "codigo": "ERROR_VALIDACION",
                  "mensaje": "No se puede eliminar: hay tarifas asociadas a este tipo de tarifa."
                }
            """)))
})
public interface SwaggerErrorResponsesTipoTarifas {}

