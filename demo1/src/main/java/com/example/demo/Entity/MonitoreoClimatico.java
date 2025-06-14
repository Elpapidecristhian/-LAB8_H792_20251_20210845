package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "monitoreo_climatico")

@Getter
@Setter
public class MonitoreoClimatico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ciudad;

    private LocalDate fecha;

    private double temperaturaPromedio;

    private String condicionFrecuente;

    private double temperaturaMaxima;

    private double temperaturaMinima;

    // Getters y setters
}
