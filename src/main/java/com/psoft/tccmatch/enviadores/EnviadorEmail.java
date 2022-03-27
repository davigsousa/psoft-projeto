package com.psoft.tccmatch.enviadores;

import java.util.List;

public interface EnviadorEmail {
    void enviar(List<Object> dados);
}
