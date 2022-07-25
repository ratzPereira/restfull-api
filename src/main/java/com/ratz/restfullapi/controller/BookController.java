package com.ratz.restfullapi.controller;

import com.ratz.restfullapi.DTO.v1.BookDTO;
import com.ratz.restfullapi.service.impl.BookServiceImpl;
import com.ratz.restfullapi.utils.MediaTypeUtils;
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

@RestController
@RequestMapping("/api/book/v1")
@Tag(name = "Book", description = "Endpoints for Managing Book")
public class BookController {

  @Autowired
  private BookServiceImpl service;

  @GetMapping(
      produces = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YML})
  @Operation(summary = "Finds all Book", description = "Finds all Book",
      tags = {"Book"},
      responses = {
          @ApiResponse(description = "Success", responseCode = "200",
              content = {
                  @Content(
                      mediaType = "application/json",
                      array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))
                  )
              }),
          @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
          @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
          @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
      }
  )
  public List<BookDTO> findAll() {
    return service.findAll();
  }

  @GetMapping(value = "/{id}",
      produces = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YML})
  @Operation(summary = "Finds a Book", description = "Finds a Book",
      tags = {"Book"},
      responses = {
          @ApiResponse(description = "Success", responseCode = "200",
              content = @Content(schema = @Schema(implementation = BookDTO.class))
          ),
          @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
          @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
          @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
          @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
      }
  )
  public BookDTO findById(@PathVariable(value = "id") Long id) {
    return service.findById(id);
  }

  @PostMapping(
      consumes = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YML},
      produces = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YML})
  @Operation(summary = "Adds a new Book",
      description = "Adds a new Book by passing in a JSON, XML or YML representation of the book!",
      tags = {"Book"},
      responses = {
          @ApiResponse(description = "Success", responseCode = "200",
              content = @Content(schema = @Schema(implementation = BookDTO.class))
          ),
          @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
          @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
      }
  )
  public BookDTO create(@RequestBody BookDTO book) {
    return service.create(book);
  }

  @PutMapping(
      consumes = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YML},
      produces = {MediaTypeUtils.APPLICATION_JSON, MediaTypeUtils.APPLICATION_XML, MediaTypeUtils.APPLICATION_YML})
  @Operation(summary = "Updates a Book",
      description = "Updates a Book by passing in a JSON, XML or YML representation of the book!",
      tags = {"Book"},
      responses = {
          @ApiResponse(description = "Updated", responseCode = "200",
              content = @Content(schema = @Schema(implementation = BookDTO.class))
          ),
          @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
          @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
          @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
      }
  )
  public BookDTO update(@RequestBody BookDTO book) {
    return service.update(book);
  }


  @DeleteMapping(value = "/{id}")
  @Operation(summary = "Deletes a Book",
      description = "Deletes a Book by passing in a JSON, XML or YML representation of the book!",
      tags = {"Book"},
      responses = {
          @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
          @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
          @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
          @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
          @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
      }
  )
  public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }
}
