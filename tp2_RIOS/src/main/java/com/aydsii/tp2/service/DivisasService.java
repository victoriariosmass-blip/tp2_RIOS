package com.aydsii.tp2.service;

import com.aydsii.tp2.dto.ConversionResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import java.util.Map;
import com.aydsii.tp2.exception.ExternalApiConnectionException;
import com.aydsii.tp2.exception.MonedaNoSoportadaException;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class DivisasService {

    //indtancia de cliente http
    private final RestClient restClient = RestClient.builder().build();

    public ConversionResponseDTO convertir(Double monto, String origen, String destino) {
        //URL dinámica
        String url = String.format("https://api.frankfurter.dev/v2/rate/%s/%s", origen, destino);

        try {
            //petición http GET
            //como el json de frankfurter es complejo, lo mapeamos temporalmente a un map genérico
            Map<String, Object> response = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(Map.class);

            //extraer los datos del json de frankfurter,cast por si devuelve int
            Double tasaCambio = ((Number) response.get("rate")).doubleValue();
            //calculo de valor final
            Double montoConvertido = monto * tasaCambio;
            String fecha = (String) response.get("date");
            //dto limpio propio
            return new ConversionResponseDTO(monto, origen, destino, tasaCambio, montoConvertido, fecha);

        } catch (HttpClientErrorException e) {
            // HttpClientErrorException atrapa errores 4xx (ej. moneda inexistente)
            throw new MonedaNoSoportadaException("La moneda solicitada no existe o no es soportada por Frankfurter.");
            
        } catch (RestClientException e) {
            // RestClientException es más general, atrapa timeouts y caídas (5xx)
            throw new ExternalApiConnectionException("El servicio externo de cotizaciones no responde.");
        }
    }
}