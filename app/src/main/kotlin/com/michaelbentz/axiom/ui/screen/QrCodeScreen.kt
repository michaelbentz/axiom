package com.michaelbentz.axiom.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.michaelbentz.axiom.R
import com.michaelbentz.axiom.viewmodel.QrCodeViewModel

@Composable
fun QrCodeScreen() {
    val qrCodeViewModel: QrCodeViewModel = viewModel()
    val latestQrCodeState = qrCodeViewModel.latestQrCode.observeAsState()
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.TopCenter),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(modifier = Modifier.height(12.dp))
                var data by remember {
                    mutableStateOf(TextFieldValue(String()))
                }
                OutlinedTextField(
                    maxLines = 7,
                    minLines = 7,
                    singleLine = false,
                    label = {
                        Text(text = stringResource(id = R.string.data))
                    },
                    value = data,
                    onValueChange = {
                        data = it
                    }
                )
                Spacer(modifier = Modifier.height(12.dp))
                Button(onClick = {
                    if (data.text.isNotEmpty()) {
                        focusManager.clearFocus()
                        qrCodeViewModel.createQrCode(data.text)
                    }
                }) {
                    Text(
                        text = stringResource(id = R.string.generate)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
                Icon(
                    Icons.Filled.KeyboardArrowDown,
                    null,
                )
                Spacer(modifier = Modifier.height(12.dp))

                val latestQrCode = latestQrCodeState.value
                if (latestQrCode != null) {

                    Spacer(modifier = Modifier.height(12.dp))
                    QrCodeImage(bytes = latestQrCode.bytes)
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(text = latestQrCode.millis.toString())
                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun QrCodeImage(bytes: ByteArray) {
    Box(
        modifier = Modifier
            .background(
                colorResource(id = R.color.white)
            )
    ) {
        GlideImage(
            model = bytes,
            contentDescription = null,
            modifier = Modifier.padding(12.dp)
        )
    }
}