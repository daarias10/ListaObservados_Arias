package ec.edu.espe.arquitectura.servidor2_arias.service;

import ec.edu.espe.arquitectura.servidor2_arias.dto.TransaccionDTO;
import java.util.Arrays;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransaccionClientService {
  private static final String BASE_URL = "http://localhost:8080/transaccion";
  private final RestTemplate restTemplate;

  public TransaccionClientService() {
    this.restTemplate = new RestTemplate(getClientHttpRequestFactory());
  }

  public List<TransaccionDTO> obtainAll() {
    ResponseEntity<TransaccionDTO[]> response =
        this.restTemplate.getForEntity(BASE_URL, TransaccionDTO[].class);
    TransaccionDTO[] objectArray = response.getBody();

    assert objectArray != null;
    return Arrays.asList(objectArray);
  }

  private static ClientHttpRequestFactory getClientHttpRequestFactory() {
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
        new HttpComponentsClientHttpRequestFactory();
    int connectTimeout = 5000;
    int readTimeout = 5000;
    clientHttpRequestFactory.setConnectTimeout(connectTimeout);
    clientHttpRequestFactory.setReadTimeout(readTimeout);
    return clientHttpRequestFactory;
  }
}
