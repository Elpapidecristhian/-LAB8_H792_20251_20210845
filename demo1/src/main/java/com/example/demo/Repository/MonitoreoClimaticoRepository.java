package com.example.demo.Repository;

import com.example.demo.Entity.MonitoreoClimatico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoreoClimaticoRepository  extends JpaRepository<MonitoreoClimatico, Integer> {
}
