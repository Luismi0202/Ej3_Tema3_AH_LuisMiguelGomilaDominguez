
package com.example.demo.dto

data class PaisDto(
    val id: Long? = null,
    val nombre: String,
    val codigoIso: String,
    val capital: String,
    val poblacionMillones: Double, // ej. 48 para 48.000.000
    val superficieKm2: Double,
    val moneda: String,
    val idiomaPrincipal: String,
    val continente: String
)