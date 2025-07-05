package com.jah.mapeo.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import com.jah.mapeo.R
import com.jah.mapeo.util.DatePickerUtils

@Composable
fun CampoFecha(
    valor: String,
    etiqueta: String,
    onFechaSeleccionada: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                DatePickerUtils.mostrarSelectorFecha(context, onFechaSeleccionada)
            }
    ) {
        OutlinedTextField(
            value = valor,
            onValueChange = {},
            label = { Text(etiqueta) },
            readOnly = true,
            enabled = false,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                disabledBorderColor = colorResource(id = R.color.barras_letra),
                disabledTextColor = colorResource(id = R.color.barras_letra),
                disabledLabelColor = colorResource(id = R.color.barras_letra),
                disabledPlaceholderColor = colorResource(id = R.color.barras_letra)
            )
        )
    }
}