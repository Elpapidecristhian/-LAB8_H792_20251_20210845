package com.example.demo.Controllers;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.Dto.MonitoreoDTO;
import com.example.demo.Dto.PronosticoPorHoraDTO;
import com.example.demo.Entity.MonitoreoClimatico;
import com.example.demo.Repository.MonitoreoClimaticoRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/clima")
public class ClimaController {

    @Autowired
    private MonitoreoClimaticoRepository monitoreoRepository;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrarMonitoreo(@RequestBody MonitoreoDTO dto) {
        MonitoreoClimatico registro = new MonitoreoClimatico();
        registro.setCiudad(dto.getCiudad());
        registro.setFecha(dto.getFecha());
        registro.setTemperaturaPromedio(dto.getTemperaturaPromedio());
        registro.setCondicionFrecuente(dto.getCondicionFrecuente());
        registro.setTemperaturaMaxima(dto.getTemperaturaMaxima());
        registro.setTemperaturaMinima(dto.getTemperaturaMinima());

        monitoreoRepository.save(registro);
        return ResponseEntity.ok("Registro guardado correctamente");
    }

    @GetMapping("/pronostico")
    public ResponseEntity<List<PronosticoPorHoraDTO>> obtenerPronosticoPorHora(@RequestParam String ciudad) {
        String apiKey = "a8e5e21e2061415e89213225251406";
        String url = "https://api.weatherapi.com/v1/forecast.json?key=" + apiKey + "&q=" + ciudad + "&days=1";

        WebClient webClient = WebClient.create();
        JsonNode response = webClient.get().uri(url).retrieve().bodyToMono(JsonNode.class).block();

        List<PronosticoPorHoraDTO> lista = new ArrayList<>();
        JsonNode horas = response.get("forecast").get("forecastday").get(0).get("hour");

        for (JsonNode hora : horas) {
            PronosticoPorHoraDTO dto = new PronosticoPorHoraDTO();
            dto.setHora(hora.get("time").asText());
            dto.setTemperatura(hora.get("temp_c").asDouble());
            dto.setCondicion(hora.get("condition").get("text").asText());
            lista.add(dto);
        }

        return ResponseEntity.ok(lista);
    }

}
