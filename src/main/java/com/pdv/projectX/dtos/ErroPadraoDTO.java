package com.pdv.projectX.dtos;

import java.time.LocalDateTime;
import java.util.List;

public record ErroPadraoDTO(
        LocalDateTime timeStamp,
        Integer status,
        String erro,
        String caminho,
        List<String> mensagens

) {
}
