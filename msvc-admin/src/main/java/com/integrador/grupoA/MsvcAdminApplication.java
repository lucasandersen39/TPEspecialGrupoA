package com.integrador.grupoA;

import com.integrador.grupoA.services.CuentaService;
import com.integrador.grupoA.services.TarifaService;
import com.integrador.grupoA.services.TipoTarifaService;
import com.integrador.grupoA.services.dto.cuentas.cuentaRequest.CuentaRequestDTO;
import com.integrador.grupoA.services.dto.tarifas.tarifaRequest.TarifaRequestDTO;
import com.integrador.grupoA.services.dto.tipoTarifas.tipoTarifaRequest.TipoTarifaRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class MsvcAdminApplication {
	public static void main(String[] args) {
		SpringApplication.run(MsvcAdminApplication.class, args);
	}

	@Autowired
	private CuentaService cuentaService;

	@Autowired
	private TipoTarifaService tipoTarifaService;

	@Autowired
	private TarifaService tarifaService;

	@Bean
	CommandLineRunner init() {
		return args -> {
			if (cuentaService.findCuentaByNumeroCuenta(2657819) == null) {
				CuentaRequestDTO defaultACuenta = new CuentaRequestDTO(
						"BBVA", 2657819, 15000.0, 56L
				);
				cuentaService.agregarCuenta(defaultACuenta);
			}

			if(tipoTarifaService.findByTipoTarifa("BASICO") == null){
				TipoTarifaRequestDTO defaultTipoTarifa = new TipoTarifaRequestDTO("BASICO");
				tipoTarifaService.crearTipoTarifa(defaultTipoTarifa);
			}

			if(tarifaService.obtenerTarifasPorTipo("BASICO").isEmpty()){
				LocalDate fecha = LocalDate.parse("2025-05-29");
				TarifaRequestDTO defaultTarifa = new TarifaRequestDTO(
						"BASICO", 200.50, fecha
				);
				tarifaService.agregarTarifa(defaultTarifa);
			}
		};
	}


}
