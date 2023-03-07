package com.example.colorpicker

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.colorpicker.ui.theme.ColorPickerTheme
import com.github.skydoves.colorpicker.compose.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColorPickerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    HomeScreen()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    val controller = rememberColorPickerController()
    val viewModel: HomeViewModel = viewModel()
    val colorUiState = viewModel.colorUiState.collectAsState().value
    Column(modifier = Modifier.background(color = colorUiState.color)) {
        HsvColorPicker(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .padding(10.dp),
            controller = controller,
            onColorChanged = {
                viewModel.updateColor(it.color)
                Log.d("Color", it.hexCode)
            }
        )
        AlphaSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(40.dp),
            controller = controller,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Card(elevation = 10.dp) {
            AlphaTile(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(40.dp)
                    .clip(RoundedCornerShape(6.dp)),
                controller = controller
            )
        }

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    ColorPickerTheme {
        HomeScreen()
    }
}