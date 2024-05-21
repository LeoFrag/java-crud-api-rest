package com.curso.apirest.apirest.Entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data



public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String nombre;
    private double precio;

}
