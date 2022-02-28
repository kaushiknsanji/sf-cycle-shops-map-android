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
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.MarkerOptions
import com.google.codelabs.myfirstmap.databinding.ActivityMainBinding
import com.google.codelabs.myfirstmap.place.Place
import com.google.codelabs.myfirstmap.place.PlacesReader

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // Get list of Places from the file "places.json"
    private val places: List<Place> by lazy {
        PlacesReader(this).read()
    }

    // Bicycle Icon to use for the Marker
    private val bicycleIcon: BitmapDescriptor by lazy {
        BitmapHelper.vectorToBitmapDescriptor(
            this,
            R.drawable.ic_directions_bike_black_24dp,
            ContextCompat.getColor(this, R.color.colorPrimary)
        )
    }

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
                // Represent Places as Markers on the Map
                addMarkers(this)
                // Customize Marker's Info Window via its Adapter
                setInfoWindowAdapter(MarkerInfoWindowAdapter(this@MainActivity))
            }
        }
    }

    /**
     * Adds Marker representations of the Places on the provided [googleMap].
     */
    private fun addMarkers(googleMap: GoogleMap) {
        places.forEach { place: Place ->
            googleMap.addMarker(
                MarkerOptions()
                    .title(place.name)
                    .position(place.latLng)
                    .icon(bicycleIcon)
            )?.also { marker ->
                // Set Place information as part of the Tag property
                // so that it can be referenced within MarkerInfoWindowAdapter
                marker.tag = place
            }
        }
    }
}
