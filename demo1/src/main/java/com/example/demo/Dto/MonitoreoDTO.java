package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class MonitoreoDTO {
    private String ciudad;
    private LocalDate fecha;
    private double temperaturaPromedio;
    private String condicionFrecuente;
    private double temperaturaMaxima;
    private double temperaturaMinima;

}
