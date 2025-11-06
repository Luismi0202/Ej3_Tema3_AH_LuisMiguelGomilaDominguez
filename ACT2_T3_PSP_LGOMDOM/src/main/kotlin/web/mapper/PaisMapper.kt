// src/main/kotlin/com/example/demo/mapper/PaisMapper.kt
package com.example.demo.mapper

import com.example.demo.dto.PaisDto
import com.example.demo.model.Pais
import org.springframework.stereotype.Component

@Component
class PaisMapper {
    fun toDto(pais: Pais): PaisDto =
        PaisDto(
            id = pais.id,
            nombre = pais.nombre,
            codigoIso = pais.codigoIso,
            capital = pais.capital,
            poblacionMillones = pais.poblacion.toDouble() / 1_000_000.0,
            superficieKm2 = pais.superficieKm2,
            moneda = pais.moneda,
            idiomaPrincipal = pais.idiomaPrincipal,
            continente = pais.continente
        )

    fun toEntity(dto: PaisDto): Pais =
        Pais(
            id = dto.id ?: 0,
            nombre = dto.nombre,
            codigoIso = dto.codigoIso,
            capital = dto.capital,
            poblacion = (dto.poblacionMillones * 1_000_000.0).toLong(),
            superficieKm2 = dto.superficieKm2,
            moneda = dto.moneda,
            idiomaPrincipal = dto.idiomaPrincipal,
            continente = dto.continente
        )
}