package com.jah.mapeo.contralador

fun obtenerExpiracionDeToken(token: String): Long? {
    if (token.isBlank()) return null

    val partes = token.split(".")
    if (partes.size != 3) return null

    return try {
        val payload = String(android.util.Base64.decode(partes[1], android.util.Base64.URL_SAFE))
        val regex = Regex("\"exp\":(\\d+)")
        regex.find(payload)?.groups?.get(1)?.value?.toLongOrNull()
    } catch (e: Exception) {
        null
    }
}


fun estaExpirado(exp: Long): Boolean {
    val ahora = System.currentTimeMillis() / 1000
    return ahora >= exp
}
