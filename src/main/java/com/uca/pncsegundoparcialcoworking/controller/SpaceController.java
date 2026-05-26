package com.uca.pncsegundoparcialcoworking.controller;


import com.uca.pncsegundoparcialcoworking.dto.GeneralResponse;
import com.uca.pncsegundoparcialcoworking.dto.request.CreateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.dto.request.UpdateSpaceRequest;
import com.uca.pncsegundoparcialcoworking.service.SpaceService;
import com.uca.pncsegundoparcialcoworking.utils.SpaceType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/spaces")
@RequiredArgsConstructor
public class SpaceController {

    private final SpaceService spaceService;

    @PostMapping
    public ResponseEntity<GeneralResponse> createSpace(@RequestBody @Valid CreateSpaceRequest request) {
        return buildResponse("Espacio registrado exitosamente.", HttpStatus.CREATED, spaceService.createSpace(request));
    }

    @GetMapping
    public ResponseEntity<GeneralResponse> getAllSpaces(
            @RequestParam(required = false) SpaceType type,
            @RequestParam(required = false) Boolean available) {
        return buildResponse("Listado de espacios recuperado correctamente.", HttpStatus.OK, spaceService.getSpaces(type, available));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralResponse> getSpaceById(@PathVariable Long id) {
        return buildResponse("Espacio encontrado exitosamente.", HttpStatus.OK, spaceService.getSpaceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeneralResponse> updateSpace(@PathVariable Long id, @RequestBody @Valid UpdateSpaceRequest request) {
        return buildResponse("Espacio actualizado exitosamente.", HttpStatus.OK, spaceService.updateSpace(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpace(@PathVariable Long id) {
        spaceService.deleteSpace(id);
        return ResponseEntity.noContent().build(); // Retorna un código 204 limpio esperado por la rúbrica
    }

    private ResponseEntity<GeneralResponse> buildResponse(String message, HttpStatus status, Object data) {
        String uri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().getPath();
        return ResponseEntity.status(status).body(
                GeneralResponse.builder()
                        .uri(uri)
                        .message(message)
                        .status(status.value())
                        .time(LocalDateTime.now())
                        .data(data)
                        .build()
        );
    }
}