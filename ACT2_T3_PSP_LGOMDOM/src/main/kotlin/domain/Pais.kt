// src/main/kotlin/com/example/demo/model/Pais.kt
package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "paises")
data class Pais(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(length = 100, nullable = false)
    var nombre: String = "",

    @Column(name = "codigo_iso", length = 3, nullable = false)
    var codigoIso: String = "",

    @Column(length = 100)
    var capital: String = "",

    @Column(nullable = false)
    var poblacion: Long = 0, // en unidades (ej. 48000000)

    @Column(name = "superficie_km2")
    var superficieKm2: Double = 0.0,

    @Column(length = 50)
    var moneda: String = "",

    @Column(name = "idioma_principal", length = 50)
    var idiomaPrincipal: String = "",

    @Column(length = 30)
    var continente: String = ""
)