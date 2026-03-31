/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Timestamp;
import java.time.LocalDate;

/**
 *
 * @author gaigu
 */
public class PlantaoBean {
    private int id;
    private int hospital_id;
    private String titulo;
    private String especialidade;
    private LocalDate data_plantao;
    private Timestamp hora_inicio;
    private Timestamp hora_fim;
    private double valor;
    private String status;

    public PlantaoBean() {
    }
            
    public PlantaoBean(int id, String titulo, String especialidade, LocalDate data_plantao, Timestamp hora_inicio, Timestamp hora_fim, double valor, String status) {
        this.id = id;
        this.titulo = titulo;
        this.especialidade = especialidade;
        this.data_plantao = data_plantao;
        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
        this.valor = valor;
        this.status = status;
    }


    public PlantaoBean(int id, int hospital_id, String titulo, String especialidade, LocalDate data_plantao, Timestamp hora_inicio, Timestamp hora_fim, double valor, String status) {
        this.id = id;
        this.hospital_id = hospital_id;
        this.titulo = titulo;
        this.especialidade = especialidade;
        this.data_plantao = data_plantao;
        this.hora_inicio = hora_inicio;
        this.hora_fim = hora_fim;
        this.valor = valor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public LocalDate getData_plantao() {
        return data_plantao;
    }

    public void setData_plantao(LocalDate data_plantao) {
        this.data_plantao = data_plantao;
    }

    public Timestamp getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Timestamp hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Timestamp getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(Timestamp hora_fim) {
        this.hora_fim = hora_fim;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public java.util.Date getData_plantao_asDate() {
        return java.sql.Date.valueOf(this.data_plantao); // se data_plantao for LocalDate
    }
    
}
