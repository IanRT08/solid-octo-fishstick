package mx.edu.utez.appgps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import mx.edu.utez.appgps.ui.AppNavigation
import org.osmdroid.config.Configuration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Configurar OSM
        Configuration.getInstance().load(
            this,
            getSharedPreferences("osm", MODE_PRIVATE)
        )

        setContent {
            AppNavigation()
        }
    }
}