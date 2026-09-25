package com.aydsii.tp2.service;

import com.aydsii.tp2.dto.ConversionResponseDTO2;
import com.aydsii.tp2.dto.HistorialTasaDTO;
import com.aydsii.tp2.exception.ExternalApiConnectionException;
import com.aydsii.tp2.exception.MonedaNoSoportadaException;
import com.aydsii.tp2.model.HistorialConversion;
import com.aydsii.tp2.repository.HistorialConversionRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class DivisasService2 {

    private final HistorialConversionRepository repository;
    private final RestTemplate restTemplate;

    public DivisasService2(HistorialConversionRepository repository) {
        this.repository = repository;
        this.restTemplate = new RestTemplate();
    }

    public HistorialConversion consultarYGuardar(String origen, String destino, BigDecimal monto) {
        String url = String.format("https://api.frankfurter.dev/v2/rate/%s/%s", origen, destino);

        ConversionResponseDTO2 responseApi; 
        
        try {
            //informar a resttemplate del dto2
            responseApi = restTemplate.getForObject(url, ConversionResponseDTO2.class);
        } catch (HttpClientErrorException e) {
            throw new MonedaNoSoportadaException("Moneda no soportada por la API o ruta inválida: " + origen + " a " + destino);
        } catch (Exception e) {
            throw new ExternalApiConnectionException("Error al conectar con la API de Frankfurter");
        }

        Double tasaExtraida = null;
        if (responseApi != null) {
            if (responseApi.getRate() != null) {
                tasaExtraida = responseApi.getRate();
            } else if (responseApi.getRates() != null && responseApi.getRates().containsKey(destino)) {
                tasaExtraida = responseApi.getRates().get(destino);
            }
        }

        if (tasaExtraida == null) {
            throw new MonedaNoSoportadaException("No se encontró la tasa de cambio para " + destino);
        }

        BigDecimal tasa = BigDecimal.valueOf(tasaExtraida);
        BigDecimal montoConvertido = monto.multiply(tasa);

        HistorialConversion historial = new HistorialConversion();
        historial.setMonedaOrigen(origen);
        historial.setMonedaDestino(destino);
        historial.setMonto(monto);
        historial.setMontoConvertido(montoConvertido);
        historial.setTasa(tasa);
        historial.setFechaConsulta(LocalDateTime.now());

        return repository.save(historial);
    }

    public List<HistorialTasaDTO> obtenerHistorial(String origen, String destino) {
        List<HistorialConversion> conversiones = repository
                .findByMonedaOrigenAndMonedaDestinoOrderByFechaConsultaDesc(origen.toUpperCase(), destino.toUpperCase());

        return conversiones.stream().map(conversion -> {
            HistorialTasaDTO dto = new HistorialTasaDTO();
            dto.setFecha(conversion.getFechaConsulta());
            dto.setTasaCambio(conversion.getTasa());
            return dto;
        }).collect(Collectors.toList());
    }
}