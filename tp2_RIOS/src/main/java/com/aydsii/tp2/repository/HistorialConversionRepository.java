package com.aydsii.tp2.repository;

import com.aydsii.tp2.model.HistorialConversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistorialConversionRepository extends JpaRepository<HistorialConversion, Integer>{
    
    //SELECT * WHERE monedaOrigen = ? AND monedaDestino = ? ORDER BY fechaConsulta DESC
    List<HistorialConversion> findByMonedaOrigenAndMonedaDestinoOrderByFechaConsultaDesc(String origen, String destino);

}