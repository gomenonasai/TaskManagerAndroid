package com.example.prueba1;

public class Task {
    // atributos de la tarea
    private String titulo;
    private String descripcion;
    private String categoria;
    private String prioridad;
    private boolean importante;
    private float urgencia;

    private boolean completada; // estado de la tarea

    // Constructor
    public Task(String titulo, String descripcion, String categoria, String prioridad, boolean importante, float urgencia) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.prioridad = prioridad;
        this.importante = importante;
        this.urgencia = urgencia;
        this.completada = false; // por defecto, la tarea se crea como "no completada"
    }

    // getters y setters
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getCategoria() { return categoria; }
    public String getPrioridad() { return prioridad; }
    public boolean isImportante() { return importante; }
    public float getUrgencia() { return urgencia; }

    public boolean isCompletada() { return completada; }
    public void setCompletada(boolean completada) {
        this.completada = completada;
    }
}
