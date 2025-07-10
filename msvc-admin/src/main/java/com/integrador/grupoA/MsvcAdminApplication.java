package com.integrador.grupoA;

import com.integrador.grupoA.entities.Cuenta;
import com.integrador.grupoA.entities.Tarifa;
import com.integrador.grupoA.entities.TipoTarifa;
import com.integrador.grupoA.repository.CuentaRepository;
import com.integrador.grupoA.repository.TarifaRepository;
import com.integrador.grupoA.repository.TipoTarifaRepository;
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
	private CuentaRepository cuentaRepository;

	@Autowired
	private TipoTarifaRepository tipoTarifaRepository;

	@Autowired
	private TarifaRepository tarifaRepository;

	@Bean
	CommandLineRunner init() {
		return args -> {
			if (cuentaRepository.findCuentaByNumeroCuenta(2657819) == null) {
				Cuenta defaultACuenta = new Cuenta("BBVA", 2657819, 15000.0, 56L);
				cuentaRepository.save(defaultACuenta);
			}

			if(tipoTarifaRepository.findByTipoTarifa("BASICO").isEmpty()) {
				TipoTarifa defaultTipoTarifa = new TipoTarifa("BASICO");
				tipoTarifaRepository.save(defaultTipoTarifa);

				if (tarifaRepository.findTarifasPorTipo("BASICO").isEmpty()) {
					LocalDate fecha = LocalDate.parse("2025-05-29");
					Tarifa defaultTarifa = new Tarifa(defaultTipoTarifa, 200.50, fecha);
					tarifaRepository.save(defaultTarifa);
				}
			}
		};
	}


}
