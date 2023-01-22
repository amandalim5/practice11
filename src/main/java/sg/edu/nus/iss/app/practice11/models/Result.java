package sg.edu.nus.iss.app.practice11.models;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Result implements Serializable{
  private String fname = "anna";
  private String sname = "tim";
  private Integer percentage;
  private String result;
  private String compatibility;


  public String getFname() {
    return fname;
  }
  public void setFname(String fname) {
    this.fname = fname;
  }
  public String getSname() {
    return sname;
  }
  public void setSname(String sname) {
    this.sname = sname;
  }
  public Integer getPercentage() {
    return percentage;
  }
  public void setPercentage(Integer percentage) {
    this.percentage = percentage;
  }
  public String getResult() {
    return result;
  }
  public void setResult(String result) {
    this.result = result;
  }
  public String getCompatibility() {
    return compatibility;
  }
  public void setCompatibility(String compatibility) {
    this.compatibility = compatibility;
  }
  

  public static Result create(String json)throws IOException{
    Result r = new Result();
    try(InputStream is = new ByteArrayInputStream(json.getBytes())){
      JsonReader reader = Json.createReader(is);
      JsonObject o = reader.readObject();
      r.setFname(o.getString("fname"));
      r.setSname(o.getString("sname"));
      r.setPercentage(Integer.parseInt(o.getString("percentage")));
      r.setResult(o.getString("result"));
      if(r.percentage >= 75){
        r.setCompatibility("Compatible");
      } else if (r.percentage > 0){
        r.setCompatibility("Not Compatible");
      } else{
        r.setCompatibility("I am a teapot!");
      }
    }
    return r;
  }

}
