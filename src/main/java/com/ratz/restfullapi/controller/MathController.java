package com.ratz.restfullapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class MathController {

  private final AtomicLong counter = new AtomicLong();

  @GetMapping("/sum/{numberOne}/{numberTwo}")
  public Double sum(@PathVariable(name = "numberOne") String numberOne,
                    @PathVariable(name = "numberTwo") String numberTwo) throws Exception {

    if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
      throw new Exception();
    }
    return convertToDouble(numberOne) + convertToDouble(numberTwo);
  }

  private Double convertToDouble(String number) {

    if(number == null) return 0D;

    String filteredNumber = number.replaceAll(",", ".");

    if(isNumeric(filteredNumber))return  Double.parseDouble(filteredNumber);

    return 0D;
  }

  private boolean isNumeric(String numberOne) {

    if(numberOne == null) return false;

    String number = numberOne.replaceAll(",", ".");
    return number.matches("[-+]?[0-9]*\\.?[0-9]+");
  }
  
}
