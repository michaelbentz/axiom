package com.michaelbentz.axiom.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.michaelbentz.axiom.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(id = R.string.app_name))
                },
            )
        },
    ) { innerPadding ->
        Surface(modifier = Modifier.padding(innerPadding)) {
            content()
        }
    }
}

@Composable
fun Spacer() {
    Spacer(modifier = Modifier.height(12.dp))
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
            modifier = Modifier.padding(12.dp),
            contentDescription = stringResource(
                id = R.string.content_description_qr_code
            )
        )
    }
}