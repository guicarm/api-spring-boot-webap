package br.com.fiap.sprint3.controlllers.api.advice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidacaoCampoError {

    private String field;
    private String error;

}