package mx.edu.utez.appgps.ui.tracking

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import mx.edu.utez.appgps.viewmodel.MapViewModel
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Polyline
import android.content.Context
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun MapScreen(
    viewModel: MapViewModel = viewModel()
) {
    val allPoints by viewModel.allTripPoints.collectAsState()
    val context = LocalContext.current

    // Configurar OSM (importante)
    Configuration.getInstance().load(
        context,
        context.getSharedPreferences("osm", Context.MODE_PRIVATE)
    )
    Configuration.getInstance().userAgentValue = context.packageName

    AndroidView(
        factory = { context ->
            MapView(context).apply {
                setTileSource(TileSourceFactory.MAPNIK)
                setMultiTouchControls(true)

                // Configurar vista inicial
                controller.setZoom(15.0)
                controller.setCenter(GeoPoint(19.0433, -98.2019)) // Coordenadas de Puebla
            }
        },
        modifier = Modifier.fillMaxSize(),
        update = { mapView ->
            // Limpiar overlays anteriores
            mapView.overlays.clear()

            // Agrupar puntos por viaje
            val pointsByTrip = allPoints.groupBy { it.tripId }

            // Dibujar polilíneas para cada viaje
            pointsByTrip.forEach { (_, points) ->
                if (points.size >= 2) {
                    val polyline = Polyline()
                    val geoPoints = points.map { point ->
                        GeoPoint(point.latitude, point.longitude)
                    }
                    polyline.setPoints(geoPoints)
                    mapView.overlays.add(polyline)

                    // Centrar en el último punto si hay puntos
                    val lastPoint = points.last()
                    mapView.controller.animateTo(GeoPoint(lastPoint.latitude, lastPoint.longitude))
                }
            }

            mapView.invalidate() // Refrescar el mapa
        }
    )
}