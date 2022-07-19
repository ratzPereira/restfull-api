package com.ratz.restfullapi.controller;

import com.ratz.restfullapi.exceptions.UnsupportedMathOperationException;
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
      throw new UnsupportedMathOperationException("Please set numeric value");
    }
    return convertToDouble(numberOne) + convertToDouble(numberTwo);
  }


  @GetMapping("/sub/{numberOne}/{numberTwo}")
  public Double sub(@PathVariable(name = "numberOne") String numberOne,
                    @PathVariable(name = "numberTwo") String numberTwo) throws Exception {

    if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
      throw new UnsupportedMathOperationException("Please set numeric value");
    }
    return convertToDouble(numberOne) - convertToDouble(numberTwo);
  }


  @GetMapping("/multi/{numberOne}/{numberTwo}")
  public Double multi(@PathVariable(name = "numberOne") String numberOne,
                    @PathVariable(name = "numberTwo") String numberTwo) throws Exception {

    if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
      throw new UnsupportedMathOperationException("Please set numeric value");
    }
    return convertToDouble(numberOne) * convertToDouble(numberTwo);
  }

  @GetMapping("/div/{numberOne}/{numberTwo}")
  public Double div(@PathVariable(name = "numberOne") String numberOne,
                      @PathVariable(name = "numberTwo") String numberTwo) throws Exception {

    if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
      throw new UnsupportedMathOperationException("Please set numeric value");
    }
    return convertToDouble(numberOne) / convertToDouble(numberTwo);
  }

  @GetMapping("/med/{numberOne}/{numberTwo}")
  public Double med(@PathVariable(name = "numberOne") String numberOne,
                    @PathVariable(name = "numberTwo") String numberTwo) throws Exception {

    if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
      throw new UnsupportedMathOperationException("Please set numeric value");
    }
    return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
  }

  @GetMapping("/squareRoot/{numberOne}")
  public Double squareRoot(@PathVariable(name = "numberOne") String numberOne) throws Exception {

    if(!isNumeric(numberOne) ){
      throw new UnsupportedMathOperationException("Please set numeric value");
    }
    return Math.sqrt(Double.parseDouble(numberOne));
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
