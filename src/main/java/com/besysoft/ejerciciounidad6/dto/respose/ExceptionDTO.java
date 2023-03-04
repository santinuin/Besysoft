package com.besysoft.ejerciciounidad6.dto.respose;

import java.util.Map;

public class ExceptionDTO {

        private int estado;
        private String mensaje;
        private Map<String, String> detalle;

    public ExceptionDTO(int estado, String mensaje, Map<String, String> detalle) {
        this.estado = estado;
        this.mensaje = mensaje;
        this.detalle = detalle;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Map<String, String> getDetalle() {
        return detalle;
    }

    public void setDetalle(Map<String, String> detalle) {
        this.detalle = detalle;
    }
}

