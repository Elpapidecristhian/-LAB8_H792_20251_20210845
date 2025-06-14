package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PronosticoPorHoraDTO {
    private String hora;
    private double temperatura;
    private String condicion;

}
