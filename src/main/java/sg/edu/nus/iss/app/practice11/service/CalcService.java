package sg.edu.nus.iss.app.practice11.service;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import sg.edu.nus.iss.app.practice11.models.Result;

@Service
public class CalcService {
  @Autowired
  RedisTemplate<String, Object> redisTemplate;
  public Optional<Result> getResult(String f, String s)throws IOException{
    System.out.println("What is happening");
    String url = UriComponentsBuilder
        .fromUriString("https://love-calculator.p.rapidapi.com/getPercentage")
        .queryParam("fname", f.replaceAll(" ", "+"))
        .queryParam("sname",s.replaceAll(" ", "+"))
        .toUriString();

    System.out.println(url);
    final HttpHeaders headers = new HttpHeaders();
    headers.set("X-RapidAPI-Key","42e96f8adfmshdb0fa985e21f81cp1ff8fdjsn287a8806ed4f");
    headers.set("X-RapidAPI-Host","love-calculator.p.rapidapi.com");

    final HttpEntity<String> entity = new HttpEntity<String>(headers);
    RestTemplate template = new RestTemplate();
    ResponseEntity<String> resp = template.exchange(url, HttpMethod.GET, entity, String.class);
    Result r = Result.create(resp.getBody());
    
    return Optional.of(r);

  }


}
