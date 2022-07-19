package com.ratz.restfullapi.service;

public interface MathOperationsService {
  Double doSum(String numberOne, String numberTwo);

  Double doSub(String numberOne, String numberTwo);

  Double doMulti(String numberOne, String numberTwo);

  Double doDiv(String numberOne, String numberTwo);

  Double doMed(String numberOne, String numberTwo);

  Double doSquareRoot(String numberOne);
}
