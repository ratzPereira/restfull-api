package com.ratz.restfullapi.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {

  private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

  public static <O, D> D parseObejct(O origin, Class<D> destination){
    return mapper.map(origin,destination);
  }

  public static <O, D> List<D> parseListObejct(List<O> origin, Class<D> destination){

    List<D> destinationObjects = new ArrayList<>();

    for (O o: origin) {

      destinationObjects.add(mapper.map(o, destination));

    }
    return destinationObjects;
  }
}
