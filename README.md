# Add a map to your Android app

An Android application that integrates Maps SDK to display a map of bicycle shops in San Francisco. Built by following the instructions detailed in the Google Codelab **["Add a map to your Android app (Kotlin)"][Android_Maps_Codelab]**. Original code by Google for this codelab can be referred [here][Android_Maps_Repository].

![App screenshot](art/app_screenshot.png)

## What one will learn

* How to enable and integrate Maps SDK for an Android app.
* How to add Markers to Map.
* How to customize Markers with custom icon and Info window.
* How to add cluster support for Markers.
* How to draw other shapes on the Map like Circles.
* How to position Camera viewpoint programmatically and listen to the Camera changes.
* List of KTX extensions available and how to use them.

## Getting Started

* Android Studio Arctic Fox or higher with updated SDK, Gradle and Google Play services.

### Prerequisites

* [Maps SDK](https://developers.google.com/maps/get-started?authuser=1#create-project) for Android.
* [Google Cloud Platform](https://console.cloud.google.com/) account with billing enabled.

## Branches in this Repository

* **[starter-code](https://github.com/kaushiknsanji/sf-cycle-shops-map-android/tree/starter-code)**
	* This is the Starter code for the [codelab][Android_Maps_Codelab].
	* In comparison to the original [main][Android_Maps_Starter_Repository] repository, this repository uses Android `ViewBinding` to bind UI elements with its objects.
* **[master](https://github.com/kaushiknsanji/sf-cycle-shops-map-android/tree/master)**
	* This contains the Solution for the [codelab][Android_Maps_Codelab].
	* In comparison to the original [solution][Android_Maps_Solution_Repository] repository and [solution-ktx][Android_Maps_Solution_KTX_Repository] repository, this repository contains additional modifications and corrections-
		* Uses `FragmentContainerView` for displaying `SupportMapFragment` as per the suggested recommendation - [commit](https://github.com/kaushiknsanji/sf-cycle-shops-map-android/commit/5aaa9c428aa523a162bc3d361f473a401dfa7d86).
		* Reads dimensions and formatting string from resources while displaying data for custom Info Window of a Place Marker - [commit](https://github.com/kaushiknsanji/sf-cycle-shops-map-android/commit/30d84c7c97c6d9fd54e25680f62857060452dca9).
		* Uses Idiomatic Kotlin approaches while working with `GoogleMap` and `ClusterManager` objects.

# License

```
Copyright (C) 2020 Google LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

<!-- Reference Style Links are to be placed after this -->
[Android_Maps_Codelab]: https://developers.google.com/codelabs/maps-platform/maps-platform-101-android#0
[Android_Maps_Repository]: https://github.com/googlecodelabs/maps-platform-101-android
[Android_Maps_Starter_Repository]: https://github.com/googlecodelabs/maps-platform-101-android/tree/main/starter
[Android_Maps_Solution_Repository]: https://github.com/googlecodelabs/maps-platform-101-android/tree/main/solution
[Android_Maps_Solution_KTX_Repository]: https://github.com/googlecodelabs/maps-platform-101-android/tree/main/solution-ktx