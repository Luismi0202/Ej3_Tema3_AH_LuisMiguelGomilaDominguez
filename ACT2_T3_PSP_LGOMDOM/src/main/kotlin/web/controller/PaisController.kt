// src/main/kotlin/com/example/demo/controller/PaisController.kt
package com.example.demo.controller

import com.example.demo.dto.PaisDto
import com.example.demo.service.PaisService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/paises")
class PaisController(private val service: PaisService) {

    @GetMapping
    fun list(): List<PaisDto> = service.listAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): PaisDto = service.getById(id)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody dto: PaisDto): PaisDto = service.create(dto)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody dto: PaisDto): PaisDto = service.update(id, dto)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)
}