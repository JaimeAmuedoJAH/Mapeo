package com.jah.mapeo.modelo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val email: String,
    val password: String
) {
    override fun toString(): String {
        return "Usuario(id=$id, nombre='$nombre', email='$email', password='$password')"
    }
}

