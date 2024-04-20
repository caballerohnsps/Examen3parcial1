package com.example.examen3parcial1.modelo;

public class entrevista {

 String descripcion;
 String periodista;
 String fecha;
 String imagen;
 String audio;

    public entrevista() {

    }

    public entrevista(String descripcion, String periodista, String fecha, String imagen, String audio) {
        this.descripcion = descripcion;
        this.periodista = periodista;
        this.fecha = fecha;
        this.imagen = imagen;
        this.audio = audio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPeriodista() {
        return periodista;
    }

    public void setPeriodista(String periodista) {
        this.periodista = periodista;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }
}
