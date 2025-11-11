package mx.edu.utez.appgps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import mx.edu.utez.appgps.ui.AppNavigation
import mx.edu.utez.appgps.ui.theme.AppGPSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppGPSTheme {
                AppNavigation()
            }
        }
    }
}