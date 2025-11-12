package mx.edu.utez.appgps.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
data class Trip(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val startTime: Long = System.currentTimeMillis(),
    var endTime: Long? = null,
    var photolui: String? = null // Guardamos la URI de la foto como String
)
