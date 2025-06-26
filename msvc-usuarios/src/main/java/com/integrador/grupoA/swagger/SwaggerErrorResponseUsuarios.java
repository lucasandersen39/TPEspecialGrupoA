package com.integrador.grupoA.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.ErrorResponse;

@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Operación exitosa"),
        @ApiResponse(responseCode = "202", description = "Operación aceptada"),
        @ApiResponse(responseCode = "204", description = "Recurso eliminado correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                        examples = @ExampleObject(value = """
                {
                  "timestamp": "2025-06-23T10:00:00",
                  "codigo": "ERROR_VALIDACION",
                  "mensaje": "Datos inválidos o formato incorrecto"
                }
            """))),
        @ApiResponse(responseCode = "404", description = "Recurso no encontrado",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                        examples = @ExampleObject(value = """
                {
                  "timestamp": "2025-06-23T10:05:00",
                  "codigo": "RECURSO_NO_ENCONTRADO",
                  "mensaje": "No se encontró el recurso solicitado"
                }
            """))),
        @ApiResponse(responseCode = "409", description = "Conflicto por recurso duplicado",
                content = @Content(schema = @Schema(implementation = ErrorResponse.class),
                        examples = @ExampleObject(value = """
                {
                  "timestamp": "2025-06-23T10:10:00",
                  "codigo": "ENTIDAD_DUPLICADA",
                  "mensaje": "Ya existe un recurso con ese identificador"
                }
            """)))
})
public interface SwaggerErrorResponseUsuarios {}
