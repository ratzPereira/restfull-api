package com.ratz.restfullapi.controller;

import com.ratz.restfullapi.DTO.v1.PersonDTOv1;
import com.ratz.restfullapi.DTO.v2.PersonDTOv2;
import com.ratz.restfullapi.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ratz.restfullapi.utils.MediaTypeUtils.*;


@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints to Managing People!")
public class PersonController {

  @Autowired
  private PersonService personService;

  @CrossOrigin(origins = "http://localhost:8080")
  @GetMapping(value = "/{id}", produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
  @Operation(summary = "Find People by ID", description = "Find People by ID",
      tags = {"People"},
      responses = {@ApiResponse(description = "Success", responseCode = "200",
          content = {@Content(schema = @Schema(implementation = PersonDTOv1.class))}),
          @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
          @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
          @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
          @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
  public PersonDTOv1 findById(@PathVariable(value = "id") Long id) {

    return personService.findById(id);
  }

  @CrossOrigin("http://localhost:8080")
  @GetMapping(produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
  @Operation(summary = "Finds all People", description = "Finds all People",
      tags = {"People"},
      responses = {@ApiResponse(description = "Success", responseCode = "200",
          content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PersonDTOv1.class)))}),
          @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
          @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
          @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
  public List<PersonDTOv1> findAll() {

    return personService.findAll();
  }

  @CrossOrigin(origins = {"http://localhost:8080", "https://erudio.com.br"})
  @PostMapping(
      produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
      consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
  @Operation(summary = "Save one Person", description = "Save one Person",
      tags = {"People"},
      responses = {@ApiResponse(description = "Created", responseCode = "200",
          content = {@Content(schema = @Schema(implementation = PersonDTOv1.class))}),
          @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
          @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
  public PersonDTOv1 createPerson(@RequestBody PersonDTOv1 person) {

    return personService.createPerson(person);
  }

  @PostMapping(value = "/api/person/v2",
      produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
      consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
  public PersonDTOv2 createPersonV2(@RequestBody PersonDTOv2 person) {

    return personService.createPersonV2(person);
  }

  @DeleteMapping(value = "/{id}")
  @Operation(summary = "Delete People by ID", description = "Delete People by ID",
      tags = {"People"},
      responses = {@ApiResponse(description = "Deleted", responseCode = "204",
          content = {@Content}),
          @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
          @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
          @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
  public ResponseEntity<?> deletePerson(@PathVariable String id) {

    personService.deletePerson(id);
    return ResponseEntity.noContent().build();
  }

  @PutMapping(
      produces = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
      consumes = {APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
  @Operation(summary = "Update one Person", description = "Update one Person",
      tags = {"People"},
      responses = {@ApiResponse(description = "Updated", responseCode = "200",
          content = {@Content(schema = @Schema(implementation = PersonDTOv1.class))}),
          @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
          @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
          @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
          @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)})
  public PersonDTOv1 updatePerson(@RequestBody PersonDTOv1 person) {

    return personService.updatePerson(person);
  }
}
