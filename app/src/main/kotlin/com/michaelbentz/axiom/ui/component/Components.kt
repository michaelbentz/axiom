package com.michaelbentz.axiom.ui.component

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
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

@Composable
fun ImageFromByteArray(
    byteArray: ByteArray,
    contentDescription: String? = null
) {
    val imageBitmap = BitmapFactory.decodeByteArray(
        byteArray, 0, byteArray.size
    ).asImageBitmap()
    Box(
        modifier = Modifier
            .background(
                colorResource(id = R.color.white)
            )
    ) {
        Image(
            bitmap = imageBitmap,
            modifier = Modifier.padding(12.dp),
            contentDescription = contentDescription,
            contentScale = ContentScale.Crop
        )
    }
}