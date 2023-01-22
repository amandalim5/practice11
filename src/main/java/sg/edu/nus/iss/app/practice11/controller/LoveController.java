package sg.edu.nus.iss.app.practice11.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletResponse;
import sg.edu.nus.iss.app.practice11.models.Result;
import sg.edu.nus.iss.app.practice11.repository.ResultRepo;
import sg.edu.nus.iss.app.practice11.service.CalcService;

@Controller
@RequestMapping(path="/calculator")
public class LoveController {

  @Autowired CalcService calcService;

  @Autowired ResultRepo resultRepo;

  @GetMapping
  public String getResult(@RequestParam(required = true) String fname, @RequestParam(required = true) String sname, Model model, HttpServletResponse response)throws IOException{
    Optional<Result> r = calcService.getResult(fname, sname);
    model.addAttribute("result", r.get());
    resultRepo.add(fname, sname, r.get());
    if(r.get().getCompatibility().equals("I am a teapot!")){
      // response.setStatus(418);
      response.setStatus(HttpStatus.I_AM_A_TEAPOT.value());
      model.addAttribute("error", HttpStatus.I_AM_A_TEAPOT);
      System.out.println(response.getStatus());
      return "teapot";
    }
    return "result";
  }

  @GetMapping(path = "/list")
  public String getList(Model model){
    List<Result> r = resultRepo.getAll(); 
    model.addAttribute("every", r);
    return "list";
  }

  @GetMapping(path = "/{res}")
  public String getResult(@PathVariable(value = "res") String id, Model model){
    Result r = (Result) resultRepo.getresult(id).get();
    model.addAttribute("result", r);
    return "result";

  }

  
}
