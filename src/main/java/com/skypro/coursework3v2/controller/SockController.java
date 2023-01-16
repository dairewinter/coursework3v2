package com.skypro.coursework3v2.controller;

import com.skypro.coursework3v2.dto.SocksRequest;
import com.skypro.coursework3v2.exception.InsufficientSockQuantityException;
import com.skypro.coursework3v2.exception.InvalidSockException;
import com.skypro.coursework3v2.model.Colour;
import com.skypro.coursework3v2.model.Size;
import com.skypro.coursework3v2.model.Sock;
import com.skypro.coursework3v2.service.SockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sock")
public class SockController {
    private final SockService sockService;

    public SockController(SockService sockService) {
        this.sockService = sockService;
    }

    @ExceptionHandler(InvalidSockException.class)
    public ResponseEntity<String>handleInvalidException(InvalidSockException invalidSockException){
        return ResponseEntity.badRequest().body(invalidSockException.getMessage());
    }

    @ExceptionHandler(InsufficientSockQuantityException.class)
    public ResponseEntity<String>handleInsufficient(InsufficientSockQuantityException insufficientSockQuantityException){
        return ResponseEntity.badRequest().body(insufficientSockQuantityException.getMessage());
    }

    @PostMapping
    public void addSocks (@RequestBody SocksRequest socksRequest){
        sockService.addSocks(socksRequest);

    }

    @PutMapping
    public void issueSocks(@RequestBody SocksRequest socksRequest){
        sockService.issueSocks(socksRequest);
    }

    @GetMapping
    public int getSocksCount(@RequestParam(required = false, name = "colour")Colour colour,
                             @RequestParam(required = false, name = "size") Size size,
                             @RequestParam(required = false, name = "cottonMin") Integer cottonMin,
                             @RequestParam(required = false, name = "cottonMax") Integer cottonMax){
        return sockService.getSockQuantity(colour, size, cottonMin, cottonMax);
    }

    @DeleteMapping
    public void removeSocks(@RequestBody SocksRequest socksRequest){
        sockService.removeSocks(socksRequest);
    }


}
