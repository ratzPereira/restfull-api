package com.ratz.restfullapi.service.impl;

import com.ratz.restfullapi.exceptions.UnsupportedMathOperationException;
import com.ratz.restfullapi.service.MathOperationsService;
import org.springframework.stereotype.Service;

@Service
public class MathOperationsServiceImpl implements MathOperationsService {


  @Override
  public Double doSum(String numberOne, String numberTwo) {
    if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
      throw new UnsupportedMathOperationException("Please set numeric value");
    }
    return convertToDouble(numberOne) + convertToDouble(numberTwo);
  }

  @Override
  public Double doSub(String numberOne, String numberTwo) {
    if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
      throw new UnsupportedMathOperationException("Please set numeric value");
    }
    return convertToDouble(numberOne) - convertToDouble(numberTwo);
  }


  @Override
  public Double doMulti(String numberOne, String numberTwo) {
    if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
      throw new UnsupportedMathOperationException("Please set numeric value");
    }
    return convertToDouble(numberOne) * convertToDouble(numberTwo);
  }

  @Override
  public Double doDiv(String numberOne, String numberTwo) {
    if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
      throw new UnsupportedMathOperationException("Please set numeric value");
    }
    return convertToDouble(numberOne) / convertToDouble(numberTwo);
  }


  @Override
  public Double doMed(String numberOne, String numberTwo) {
    if(!isNumeric(numberOne) || !isNumeric(numberTwo)){
      throw new UnsupportedMathOperationException("Please set numeric value");
    }
    return (convertToDouble(numberOne) + convertToDouble(numberTwo)) / 2;
  }

  @Override
  public Double doSquareRoot(String numberOne) {

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
