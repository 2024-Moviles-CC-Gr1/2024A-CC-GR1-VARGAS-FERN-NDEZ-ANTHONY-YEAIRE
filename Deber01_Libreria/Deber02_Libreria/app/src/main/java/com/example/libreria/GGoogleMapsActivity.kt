package com.example.libreria

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.snackbar.Snackbar
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.model.GeocodingResult

class GGoogleMapsActivity : AppCompatActivity() {
    private lateinit var mapa: GoogleMap
    var permisos = false
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var geoApiContext: GeoApiContext

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ggoogle_maps)
        solicitarPermisos()
        iniciarLogicaMapa()
        val botonLibPrincipal = findViewById<Button>(R.id.btn_ir_libreria)
        botonLibPrincipal.setOnClickListener {
            val idLibreria = 1
            moverLibreria(idLibreria)
            obtenerUltimaDireccion { direccion ->
                if (direccion != null) {
                    obtenerCoordenadasDeDireccion(direccion) { latLng ->
                        if (latLng != null) {
                            moverCamaraConZoom(latLng, 17f)
                        } else {
                            mostrarSnackbar("No se pudo obtener las coordenadas.")
                        }
                    }
                } else {
                    mostrarSnackbar("No se pudo obtener la dirección.")
                }
            }
        }
    }

    fun solicitarPermisos(){
        val contexto = this.applicationContext
        val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
        val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
        val permisoFine = ContextCompat.checkSelfPermission(contexto, nombrePermisoFine)
        val permisoCoarse = ContextCompat.checkSelfPermission(contexto, nombrePermisoCoarse)
        val tienePermisos = permisoFine == PackageManager.PERMISSION_GRANTED &&
                permisoCoarse == PackageManager.PERMISSION_GRANTED
        if(tienePermisos){
            permisos = true
        }else{
            ActivityCompat.requestPermissions(
                this, arrayOf(nombrePermisoFine, nombrePermisoCoarse), 1
            )
        }
    }

    fun iniciarLogicaMapa(){
        val fragmentoMapa = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        fragmentoMapa.getMapAsync{ googleMap ->
            with(googleMap){
                mapa = googleMap
                establecerConfiguracionMapa()
                escucharListeners()
            }
        }
        // Initialize the GeoApiContext
        geoApiContext = GeoApiContext.Builder()
            .apiKey(getString(R.string.google_maps_key)) // Your Google Maps API Key
            .build()
    }
    fun establecerConfiguracionMapa(){
        val contexto = this.applicationContext
        with(mapa){
            val nombrePermisoFine = android.Manifest.permission.ACCESS_FINE_LOCATION
            val nombrePermisoCoarse = android.Manifest.permission.ACCESS_COARSE_LOCATION
            val permisoFine = ContextCompat.checkSelfPermission(contexto, nombrePermisoFine)
            val permisoCoarse = ContextCompat.checkSelfPermission(contexto, nombrePermisoCoarse)
            val tienePermisos = permisoFine == PackageManager.PERMISSION_GRANTED &&
                    permisoCoarse == PackageManager.PERMISSION_GRANTED
            if(tienePermisos){
                mapa.isMyLocationEnabled = true
                uiSettings.isMyLocationButtonEnabled = true
            }
            uiSettings.isZoomControlsEnabled = true
        }
    }
    fun moverLibreria(idLibreria: Int) {
        val zoom = 17f
        val coordenadasLibreria = obtenerCoordenadasLibreria(idLibreria)

        if (coordenadasLibreria != null) {
            val titulo = "Librería"
            val markLibreria = anadirMarcador(coordenadasLibreria, titulo)
            markLibreria.tag = titulo
            moverCamaraConZoom(coordenadasLibreria, zoom)
        } else {
            mostrarSnackbar("No se encontraron coordenadas para la librería.")
        }
    }

    fun moverCamaraConZoom(latlang: LatLng, zoom: Float = 10f){
        mapa.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                latlang, zoom
            )
        )
    }
    fun anadirMarcador(latLang: LatLng, title:String): Marker {
        return mapa.addMarker(
            MarkerOptions().position(latLang)
                .title(title)
        )!!
    }
    fun mostrarSnackbar(texto:String){
        val snack = Snackbar.make(
            findViewById(R.id.cl_google_maps),
            texto,
            Snackbar.LENGTH_INDEFINITE
        )
        snack.show()
    }
    fun escucharListeners(){
        mapa.setOnMarkerClickListener {
            mostrarSnackbar("setOnMarkerClickListener $it.tag")
            return@setOnMarkerClickListener true
        }
        mapa.setOnCameraMoveListener {
            mostrarSnackbar("setOnCameraMoveListener")
        }
        mapa.setOnCameraMoveStartedListener {
            mostrarSnackbar("setOnCameraMoveStartedListener")
        }
        mapa.setOnCameraIdleListener {
            mostrarSnackbar("setOnCameraIdleListener")
        }
    }
    private fun obtenerUltimaDireccion(callback: (String?) -> Unit) {
        val dbHelper = SQLiteHelperLibreria(this)
        val libreria = dbHelper.consultarLibreriaPorID(1) // Obtener la última librería
        callback(libreria?.direccion)
    }

    private fun obtenerCoordenadasDeDireccion(direccion: String, callback: (LatLng?) -> Unit) {
        val task = GeocodingApi.geocode(geoApiContext, direccion).await()
        if (task.isNotEmpty()) {
            val resultado = task[0]
            val latLng = LatLng(resultado.geometry.location.lat, resultado.geometry.location.lng)
            callback(latLng)
        } else {
            callback(null)
        }
    }
    fun obtenerCoordenadasLibreria(idLibreria: Int): LatLng? {
        val libreria = SQLiteHelperLibreria(this).consultarLibreriaPorID(idLibreria)
        return if (libreria != null) {
            // Asumiendo que el campo direccion tiene formato "lat,lng"
            val coordenadas = libreria.direccion.split(",")
            if (coordenadas.size == 2) {
                val lat = coordenadas[0].toDoubleOrNull()
                val lng = coordenadas[1].toDoubleOrNull()
                if (lat != null && lng != null) {
                    LatLng(lat, lng)
                } else {
                    null
                }
            } else {
                null
            }
        } else {
            null
        }
    }

}