package com.michaelbentz.axiom.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.michaelbentz.axiom.R
import com.michaelbentz.axiom.ui.component.QrCodeImage
import com.michaelbentz.axiom.ui.component.Spacer
import com.michaelbentz.axiom.ui.component.TopBar
import com.michaelbentz.axiom.viewmodel.QrCodeViewModel

@Composable
fun QrCodeScreen() {
    val viewModel: QrCodeViewModel = viewModel()
    val latestQrCodeState = viewModel.latestQrCode.observeAsState()
    val focusManager = LocalFocusManager.current

    TopBar {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .wrapContentSize()
                        .align(Alignment.TopCenter),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    var dataString by remember {
                        mutableStateOf(String())
                    }
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        singleLine = false,
                        minLines = 4,
                        maxLines = 4,
                        label = {
                            Text(
                                text = stringResource(id = R.string.data_string)
                            )
                        },
                        value = dataString,
                        onValueChange = {
                            dataString = it
                        }
                    )
                    Spacer()
                    Button(
                        onClick = {
                            if (dataString.isNotEmpty()) {
                                focusManager.clearFocus()
                                viewModel.createQrCode(dataString)
                            }
                        }) {
                        Text(
                            text = stringResource(id = R.string.generate_qr_code)
                        )
                    }
                    Spacer()
                    Icon(
                        Icons.Filled.KeyboardArrowDown,
                        stringResource(id = R.string.content_description_arrow_down),
                    )
                    Spacer()

                    val latestQrCode = latestQrCodeState.value
                    if (latestQrCode != null) {
                        Spacer()
                        QrCodeImage(bytes = latestQrCode.bytes)
                        Spacer()
                        Text(
                            text = latestQrCode.toString()
                        )
                    }
                }
            }
        }
    }
}