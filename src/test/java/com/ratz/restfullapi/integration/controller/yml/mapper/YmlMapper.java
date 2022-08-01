package com.ratz.restfullapi.integration.controller.yml.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class YmlMapper implements ObjectMapper {

  protected TypeFactory typeFactory;
  private Logger logger = LoggerFactory.getLogger(YmlMapper.class.getName());
  private com.fasterxml.jackson.databind.ObjectMapper objectMapper;

  public YmlMapper() {
    objectMapper = new com.fasterxml.jackson.databind.ObjectMapper(new YAMLFactory());
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    typeFactory = TypeFactory.defaultInstance();
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Object deserialize(ObjectMapperDeserializationContext context) {
    try {
      String dataToDeserialize = context.getDataToDeserialize().asString();
      Class type = (Class) context.getType();

      logger.info("Trying deserialize object of type" + type);

      return objectMapper.readValue(dataToDeserialize, typeFactory.constructType(type));
    } catch (JsonMappingException e) {
      logger.error("Error deserializing object");
      e.printStackTrace();
    } catch (JsonProcessingException e) {
      logger.error("Error deserializing object");
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public Object serialize(ObjectMapperSerializationContext context) {
    try {
      return objectMapper.writeValueAsString(context.getObjectToSerialize());
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    return null;
  }
}
