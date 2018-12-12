# android-mvvm-coroutine
Kotlin android application example with MVVM pattern, android architecture components, and kotlin coroutine.This sample is about soccer match schedules which the API i used from [theSportDb](https://www.thesportsdb.com/api.php). This sample app is based on the [Guide to app architecture](https://developer.android.com/jetpack/docs/guide) article with the [androidx](https://developer.android.com/jetpack/androidx/) package, [Kotlin 1.3](https://kotlinlang.org/docs/reference/whatsnew13.html), and production ready [coroutine](https://kotlinlang.org/docs/reference/coroutines/basics.html). I use several android architecture components like [LiveData](https://developer.android.com/jetpack/arch/livedata), [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) ,[Room](https://developer.android.com/training/data-storage/room/). Here are several libraries that i use:
* [Coroutine 1.0.0](https://github.com/Kotlin/kotlinx.coroutines#user-content-android) // Threading
* [Material 1.0.0](https://github.com/material-components/material-components-android/blob/master/docs/getting-started.md) // Design components
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [LiveData](https://developer.android.com/jetpack/arch/livedata)
* [Room](https://developer.android.com/training/data-storage/room/) // Local data storage
* [Retrofit](https://square.github.io/retrofit/) // Networking
* [Moshi](https://github.com/square/moshi) // JSON parser
* [Picasso](http://square.github.io/picasso/) // Image loader
* [Mockito](https://site.mockito.org/) // Unit test
* [Espresso](https://developer.android.com/training/testing/espresso/) // UI test

The architecture overview:

![Guide to app architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

There are several external sample i take to create this sample, primarily from this google [Github Browser Sample app](https://github.com/googlesamples/android-architecture-components/tree/master/GithubBrowserSample). For this app i use the [NetworkBoundResource](https://github.com/zcabez/android-mvvm-coroutine/blob/master/app/src/main/java/com/rifqimfahmi/foorballapps/data/source/remote/NetworkBoundResource.kt) for common implementation to fetch and cache resource from the internet. It also have abstract functions such as whether we should fetch data from internet or not. If the device is offline, the repository will return the last cached data. Almost all data returned from the repository that fetched from the network is wrapped in a [Resource](https://github.com/zcabez/android-mvvm-coroutine/blob/master/app/src/main/java/com/rifqimfahmi/foorballapps/vo/Resource.kt) object to determine the data status whether it is `loading`, `success`, or `error`.

Try the app at [Play Store](https://play.google.com/store/apps/details?id=com.rifqimfahmi.foorballapps). Feel free to create new issues for bug or request more features.