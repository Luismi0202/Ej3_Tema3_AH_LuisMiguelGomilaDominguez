// src/main/kotlin/com/example/demo/service/PaisService.kt
package com.example.demo.service

import com.example.demo.dto.PaisDto
import com.example.demo.mapper.PaisMapper
import com.example.demo.repository.PaisRepository
import org.springframework.stereotype.Service
import jakarta.persistence.EntityNotFoundException

@Service
class PaisService(
    private val repo: PaisRepository,
    private val mapper: PaisMapper
) {
    fun listAll(): List<PaisDto> = repo.findAll().map { mapper.toDto(it) }

    fun getById(id: Long): PaisDto =
        repo.findById(id).map { mapper.toDto(it) }.orElseThrow { NotFoundException("Pais $id no encontrado") }


    fun create(dto: PaisDto): PaisDto {
        val saved = repo.save(mapper.toEntity(dto))
        return mapper.toDto(saved)
    }

    fun update(id: Long, dto: PaisDto): PaisDto {
        val existing = repo.findById(id).orElseThrow { NotFoundException("Pais $id no encontrado") }
        val toSave = mapper.toEntity(dto).copy(id = existing.id)
        val saved = repo.save(toSave)
        return mapper.toDto(saved)
    }

    fun delete(id: Long) {
        if (!repo.existsById(id)) throw NotFoundException("Pais $id no encontrado")
        repo.deleteById(id)
    }
}