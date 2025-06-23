package com.integrador.grupoA.swagger;

import com.integrador.grupoA.exceptions.model.ErrorResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@ApiResponses({
        @ApiResponse(responseCode = "400", description = "Error de validación en tarifa",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                        examples = @ExampleObject(name = "MontoNegativo", value = """
                {
                  "timestamp": "2025-06-22T11:30:00",
                  "codigo": "ERROR_VALIDACION",
                  "mensaje": "El monto debe ser positivo"
                }
            """))),
        @ApiResponse(responseCode = "404", description = "Tarifa no encontrada",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                        examples = @ExampleObject(name = "TarifaNoEncontrada", value = """
                {
                  "timestamp": "2025-06-22T11:31:00",
                  "codigo": "RECURSO_NO_ENCONTRADO",
                  "mensaje": "No existen tarifas con el tipo: PREMIUM"
                }
            """))),
        @ApiResponse(responseCode = "409", description = "Conflicto por tarifa ya existente",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                        examples = @ExampleObject(name = "TarifaDuplicada", value = """
                {
                  "timestamp": "2025-06-22T11:32:00",
                  "codigo": "ENTIDAD_DUPLICADA",
                  "mensaje": "Ya existe una tarifa del tipo PREMIUM para la fecha 2025-07-01"
                }
            """)))
})
public interface SwaggerErrorResponsesTarifas {}

