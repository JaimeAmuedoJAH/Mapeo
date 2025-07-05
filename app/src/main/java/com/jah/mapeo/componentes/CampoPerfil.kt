package com.jah.mapeo.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.jah.mapeo.R

@Composable
fun CampoPerfil(valor: String, etiqueta: String) {
    val colores = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = colorResource(id = R.color.barras_letra),
        unfocusedBorderColor = colorResource(id = R.color.barras_letra),
        cursorColor = colorResource(id = R.color.barras_letra),
        focusedTextColor = colorResource(id = R.color.barras_letra)
    )

    OutlinedTextField(
        value = valor,
        onValueChange = {},
        readOnly = true,
        label = { Text(etiqueta) },
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .padding(vertical = 8.dp),
        colors = colores
    )
}