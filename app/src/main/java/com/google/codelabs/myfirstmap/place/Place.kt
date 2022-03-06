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

package com.google.codelabs.myfirstmap.place

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

data class Place(
    val name: String,
    val latLng: LatLng,
    val address: String,
    val rating: Float
) : ClusterItem {

    /**
     * The position of this marker. This must always return the same value.
     */
    override fun getPosition(): LatLng = latLng

    /**
     * The title of this marker.
     */
    override fun getTitle(): String = name

    /**
     * The description of this marker.
     */
    override fun getSnippet(): String = address

}