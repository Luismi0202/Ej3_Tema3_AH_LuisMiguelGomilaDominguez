// src/main/kotlin/com/example/demo/repository/PaisRepository.kt
package com.example.demo.repository

import com.example.demo.model.Pais
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaisRepository : JpaRepository<Pais, Long>