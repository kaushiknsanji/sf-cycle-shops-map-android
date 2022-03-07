// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.codelabs.myfirstmap

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.codelabs.myfirstmap.databinding.ActivityMainBinding
import com.google.codelabs.myfirstmap.place.Place
import com.google.codelabs.myfirstmap.place.PlaceRenderer
import com.google.codelabs.myfirstmap.place.PlacesReader
import com.google.maps.android.clustering.ClusterManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Get list of Places from the file "places.json"
    private val places: List<Place> by lazy {
        PlacesReader(this).read()
    }

    // A Circle to show around a Marker when clicked
    private var circle: Circle? = null

    /**
     * Perform initialization of all fragments.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Find the Map Fragment
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.map_fragment) as SupportMapFragment
        // Obtain the GoogleMap instance from the fragment
        mapFragment.getMapAsync { googleMap: GoogleMap ->
            with(googleMap) {
                // Represent Places as Markers on the Map with clustering support
                addClusteredMarkers(this)
            }
        }
    }

    /**
     * Adds Marker representations of the Places on the provided [googleMap] with clustering support.
     */
    private fun addClusteredMarkers(googleMap: GoogleMap) {
        // Create a Cluster Manager
        val clusterManager = ClusterManager<Place>(this, googleMap)

        with(clusterManager) {
            // Set custom renderer
            renderer = PlaceRenderer(this@MainActivity, googleMap, this)

            // Set custom Info Window
            markerCollection.setInfoWindowAdapter(MarkerInfoWindowAdapter(this@MainActivity))

            // Set OnCameraIdleListener to re-cluster based on user's pan and zoom actions on the map
            googleMap.setOnCameraIdleListener(this)

            // Feed the Markers and force cluster
            addItems(places)
            cluster()

            // Show a Circle on the ClusterItem clicked
            setOnClusterItemClickListener { clusterItem: Place ->
                addCircle(googleMap, clusterItem)
                return@setOnClusterItemClickListener false
            }
        }
    }

    /**
     * Adds a [Circle] around the provided [clusterItem] on [googleMap].
     */
    private fun addCircle(googleMap: GoogleMap, clusterItem: Place) {
        circle?.remove()
        circle = googleMap.addCircle(
            CircleOptions()
                .center(clusterItem.latLng)
                .radius(1000.0)
                .fillColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.colorPrimaryTranslucent
                    )
                )
                .strokeColor(ContextCompat.getColor(this@MainActivity, R.color.colorPrimary))
        )
    }

}