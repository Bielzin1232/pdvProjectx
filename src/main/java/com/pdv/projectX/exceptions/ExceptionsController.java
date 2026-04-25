package com.pdv.projectX.exceptions;

import com.pdv.projectX.dtos.ErroPadraoDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ExceptionsController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroPadraoDTO> tratarErroDeValidacao(MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<String> mensagensDeErro = new ArrayList<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            mensagensDeErro.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
        }

        ErroPadraoDTO erro = new ErroPadraoDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Validação de Dados",
                request.getRequestURI(),
                mensagensDeErro
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroPadraoDTO> tratarErroGenerico(Exception ex, HttpServletRequest request) {
        System.err.println("ERRO CRÍTICO no servidor: " + ex.getMessage());
        ErroPadraoDTO erro = new ErroPadraoDTO(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro Interno no Servidor",
                request.getRequestURI(),
                List.of("Ops! Ocorreu um erro inesperado. tente novamente mais tarde" )
        );

       System.err.println();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErroPadraoDTO> tratarErroDeValidacaoDoBanco(ConstraintViolationException ex, HttpServletRequest request) {

        List<String> mensagensDeErro = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            mensagensDeErro.add(violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ErroPadraoDTO erro = new ErroPadraoDTO(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Erro de Validação na Entidade",
                request.getRequestURI(),
                mensagensDeErro
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}