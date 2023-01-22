package sg.edu.nus.iss.app.practice11.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.app.practice11.models.Result;

@Repository
public class ResultRepo {
  @Autowired
  private RedisTemplate<String, Object> template;

  public void add(String fname, String sname, Result result){
    template.opsForValue().set(fname+sname, result);
  }

  public Optional<Object> getresult(String names){
    return Optional.ofNullable(template.opsForValue().get(names));

  }
  
  public List<Result> getAll(){
    Set<String> happiness = template.keys("*");
    List<Result> joy = new ArrayList<Result>();
    for(String dope: happiness){
      
      Result r = (Result) getresult(dope).get();
      // System.out.println(r.getFname());
      joy.add(r);
    }
    return joy;
  }
}
