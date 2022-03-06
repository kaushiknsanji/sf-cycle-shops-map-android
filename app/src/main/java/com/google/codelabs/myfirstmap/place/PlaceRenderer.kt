package com.google.codelabs.myfirstmap.place

import android.content.Context
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.codelabs.myfirstmap.BitmapHelper
import com.google.codelabs.myfirstmap.R
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer

/**
 * A custom [com.google.maps.android.clustering.view.ClusterRenderer] for [Place] ClusterItem objects.
 * @property context [Context] to read resources and for [DefaultClusterRenderer]
 * @param map [GoogleMap] instance required by [DefaultClusterRenderer]
 * @param clusterManager [ClusterManager] instance required by [DefaultClusterRenderer]
 */
class PlaceRenderer(
    private val context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<Place>
) : DefaultClusterRenderer<Place>(context, map, clusterManager) {

    // Bicycle Icon to use for the Marker
    private val bicycleIcon: BitmapDescriptor by lazy {
        BitmapHelper.vectorToBitmapDescriptor(
            context,
            R.drawable.ic_directions_bike_black_24dp,
            ContextCompat.getColor(context, R.color.colorPrimary)
        )
    }

    /**
     * Called before the marker for a ClusterItem is added to the map. The default implementation
     * sets the marker and snippet text based on the respective item text if they are both
     * available, otherwise it will set the title if available, and if not it will set the marker
     * title to the item snippet text if that is available.
     *
     * The first time [ClusterManager.cluster] is invoked on a set of items
     * [.onBeforeClusterItemRendered] will be called and
     * [.onClusterItemUpdated] will not be called.
     * If an item is removed and re-added (or updated) and [ClusterManager.cluster] is
     * invoked again, then [.onClusterItemUpdated] will be called and
     * [.onBeforeClusterItemRendered] will not be called.
     *
     * @param item item to be rendered
     * @param markerOptions the markerOptions representing the provided item
     */
    override fun onBeforeClusterItemRendered(item: Place, markerOptions: MarkerOptions) {
        markerOptions.title(item.name)
            .position(item.position)
            .icon(bicycleIcon)
    }

    /**
     * Called after the marker for a ClusterItem has been added to the map.
     *
     * @param clusterItem the item that was just added to the map
     * @param marker the marker representing the item that was just added to the map
     */
    override fun onClusterItemRendered(clusterItem: Place, marker: Marker) {
        marker.tag = clusterItem
    }
}