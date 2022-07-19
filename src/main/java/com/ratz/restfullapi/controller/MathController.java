package com.ratz.restfullapi.controller;

import com.ratz.restfullapi.service.MathOperationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MathController {


  @Autowired
  private MathOperationsService mathOperationsService;

  @GetMapping("/sum/{numberOne}/{numberTwo}")
  public Double sum(@PathVariable(name = "numberOne") String numberOne,
                    @PathVariable(name = "numberTwo") String numberTwo) {

    return mathOperationsService.doSum(numberOne, numberTwo);
  }


  @GetMapping("/sub/{numberOne}/{numberTwo}")
  public Double sub(@PathVariable(name = "numberOne") String numberOne,
                    @PathVariable(name = "numberTwo") String numberTwo) {

    return mathOperationsService.doSub(numberOne,numberTwo);
  }


  @GetMapping("/multi/{numberOne}/{numberTwo}")
  public Double multi(@PathVariable(name = "numberOne") String numberOne,
                    @PathVariable(name = "numberTwo") String numberTwo) {

  return mathOperationsService.doMulti(numberOne,numberTwo);
  }

  @GetMapping("/div/{numberOne}/{numberTwo}")
  public Double div(@PathVariable(name = "numberOne") String numberOne,
                      @PathVariable(name = "numberTwo") String numberTwo) {

    return mathOperationsService.doDiv(numberOne,numberTwo);

  }

  @GetMapping("/med/{numberOne}/{numberTwo}")
  public Double med(@PathVariable(name = "numberOne") String numberOne,
                    @PathVariable(name = "numberTwo") String numberTwo) {

    return mathOperationsService.doMed(numberOne,numberTwo);
  }

  @GetMapping("/squareRoot/{numberOne}")
  public Double squareRoot(@PathVariable(name = "numberOne") String numberOne) {

    return mathOperationsService.doSquareRoot(numberOne);
  }


}
