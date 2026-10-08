package pe.edu.utp.apf1.dto;

import java.math.BigDecimal;

public record ProductoResponse(Long id, String nombre, BigDecimal precio, Integer stock) {}
