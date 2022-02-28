package com.google.codelabs.myfirstmap

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.codelabs.myfirstmap.databinding.MarkerInfoContentsBinding
import com.google.codelabs.myfirstmap.place.Place

/**
 * A Class that implements [GoogleMap.InfoWindowAdapter] to customize Info Window of a [Marker].
 * @param context A [Context] to inflate the layout for the Info contents
 * and also to read [android.content.res.Resources].
 */
class MarkerInfoWindowAdapter(
    private val context: Context
) : GoogleMap.InfoWindowAdapter {
    /**
     * Provides custom contents for the default Info Window frame of the [marker].
     */
    override fun getInfoContents(marker: Marker): View? {
        // Read Place information from Marker's Tag
        // When the tag is not set, return null to use the default rendering
        val place = marker.tag as? Place ?: return null

        // Inflate the Info Window view and set the contents
        val windowBinding = MarkerInfoContentsBinding.inflate(LayoutInflater.from(context))
        with(windowBinding) {
            textMarkerTitle.text = place.name
            textMarkerAddress.text = place.address
            textMarkerRating.text = context.resources.getString(R.string.place_rating, place.rating)
        }

        // Return the Info Window to be shown
        return windowBinding.root
    }

    /**
     * Provides a custom Info Window frame to be used for the [marker].
     */
    override fun getInfoWindow(marker: Marker): View? {
        // Return null to use the default Info Window frame
        return null
    }
}