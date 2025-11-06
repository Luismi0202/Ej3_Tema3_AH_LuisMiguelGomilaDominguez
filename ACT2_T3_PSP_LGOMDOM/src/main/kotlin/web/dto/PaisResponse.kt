package com.example.demo.web.dto

data class PaisResponse(
    val id: Long,
    val nombre: String,
    val codigoIso: String,
    val capital: String?,
    val poblacion: Double, // en millones
    val superficieKm2: Double,
    val moneda: String?,
    val idiomaPrincipal: String?,
    val continente: String?
)
