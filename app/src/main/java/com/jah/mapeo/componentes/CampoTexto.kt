package com.jah.mapeo.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.jah.mapeo.R

@Composable
fun CampoTexto(
    valor: String,
    onCambio: (String) -> Unit,
    etiqueta: String,
    readOnly: Boolean = false,
    modifier: Modifier = Modifier
) {
    val colores = OutlinedTextFieldDefaults.colors(
        focusedBorderColor = colorResource(id = R.color.barras_letra),
        unfocusedBorderColor = colorResource(id = R.color.barras_letra),
        cursorColor = colorResource(id = R.color.barras_letra),
        focusedTextColor = colorResource(id = R.color.barras_letra)
    )

    OutlinedTextField(
        value = valor,
        onValueChange = onCambio,
        label = { Text(etiqueta) },
        readOnly = readOnly,
        modifier = modifier.fillMaxWidth(),
        colors = colores
    )
}