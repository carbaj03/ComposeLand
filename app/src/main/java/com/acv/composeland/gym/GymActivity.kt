package com.acv.composeland.gym

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import com.acv.composeland.ui.ComposeLandTheme
import com.acv.composeland.ui.common.ChipGroup

class GymActivity : AppCompatActivity() {

    override fun onCreate(rememberSaveable: Bundle?) {
        super.onCreate(rememberSaveable)

        setContent {
            ComposeLandTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Home()
                }
            }
        }
    }
}

@Composable
fun Home(){
    ChipGroup(
        items = listOf("sp", "em"),
        onChange = { }
    )
}