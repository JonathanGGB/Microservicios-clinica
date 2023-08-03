package com.clinic.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class GatewayServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
		
	}
	
	@GetMapping("/prueba")
	public String prueba() {
		return "Conexión de prueba";
	}
	
	@GetMapping("/error")
	public String error(){
		return "Servicio de la clínica no está disponible por el momento.";
	}

}
