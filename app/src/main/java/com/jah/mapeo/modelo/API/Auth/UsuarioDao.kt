package com.jah.mapeo.modelo.API.Auth

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jah.mapeo.modelo.POJO.Usuario

@Dao
interface UsuarioDao {

    @Insert(onConflict = OnConflictStrategy.Companion.ABORT)
    suspend fun insertar(usuario: Usuario)

    @Query("SELECT * FROM usuarios WHERE email = :email LIMIT 1")
    suspend fun obtenerPorEmail(email: String): Usuario?
}