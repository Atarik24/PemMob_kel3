package com.example.tugas_kel3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tugas_kel3.ui.theme.Tugas_kel3Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tugas_kel3Theme {
                MetamorphosisApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MetamorphosisApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Metamorfosis Kupu-Kupu",
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        MetamorphosisScreen(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun MetamorphosisScreen(modifier: Modifier = Modifier) {
    var currentIndex by remember { mutableIntStateOf(0) }
    var clickCount by remember { mutableIntStateOf(0) }

    // Target klik acak antara 2 sampai 5 khusus untuk Telur (0) dan Kepompong (2)
    var targetClickCount by remember { mutableIntStateOf(Random.nextInt(2, 5)) }

    val currentImageId = when (currentIndex) {
        0 -> R.drawable.screenshot_2026_10_05_101843 // Telur
        1 -> R.drawable.screenshot_2026_10_05_101905 // Ulat
        2 -> R.drawable.screenshot_2026_10_05_101919 // Kepompong
        3 -> R.drawable.screenshot_2026_10_05_101925 // Kupu-kupu
        else -> R.drawable.screenshot_2026_10_05_101843
    }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = currentImageId),
            contentDescription = "Metamorfosis Kupu-Kupu",
            modifier = Modifier
                .size(280.dp)
                .clickable {
                    when (currentIndex) {
                        0 -> {
                            // Telur: Butuh 2-5 klik acak
                            clickCount++
                            if (clickCount >= targetClickCount) {
                                currentIndex = 1
                                clickCount = 0
                                targetClickCount = Random.nextInt(2, 5) // Reset target acak untuk fase berikutnya
                            }
                        }
                        1 -> {
                            // Ulat: Cuma 1 kali klik
                            currentIndex = 2
                            clickCount = 0
                        }
                        2 -> {
                            // Kepompong: Butuh 2-5 klik acak
                            clickCount++
                            if (clickCount >= targetClickCount) {
                                currentIndex = 3
                                clickCount = 0
                                targetClickCount = Random.nextInt(2, 5) // Reset target acak untuk siklus berikutnya
                            }
                        }
                        3 -> {
                            // Kupu-kupu: Cuma 1 kali klik balik ke awal
                            currentIndex = 0
                            clickCount = 0
                        }
                    }
                }
        )

        Spacer(modifier = Modifier.height(24.dp))

        val teksTampil = when (currentIndex) {
            0 -> {
                val sisaKlik = targetClickCount - clickCount
                if (clickCount > 0) "Klik untuk telur pecah ($sisaKlik klik lagi)"
                else "Klik untuk membantu telur pecah"
            }
            1 -> "Klik agar ulat menjadi kepompong"
            2 -> {
                val sisaKlik = targetClickCount - clickCount
                if (clickCount > 0) "Klik agar kupu-kupu keluar ($sisaKlik klik lagi)"
                else "Klik agar kupu-kupu keluar"
            }
            else -> "Klik untuk kupu-kupu bertelur"
        }

        Text(
            text = teksTampil,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MetamorphosisPreview() {
    Tugas_kel3Theme {
        MetamorphosisApp()
    }
}