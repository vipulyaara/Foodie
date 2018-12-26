# Foodie
Demo application to show food outlets around user.


## Architecture

This app focuses on scalable, flexible and reactive app architecure. It can even be "too much code" (e.g. dependency injection) for an app this size, but it is meant to be scaled well.

I am using an MVVM with interactors as an additional layer to enhance re-usability. The app uses following frameworks


* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) (throughout the data layer; for thread switching)
* [Livedata](https://developer.android.com/topic/libraries/architecture/livedata) (between ViewModels and Fragments)
* [RxJava2](https://github.com/ReactiveX/RxJava) (enabling reactiveness on data layer)
* [Room](https://developer.android.com/topic/libraries/architecture/room)
* [Paging Library](https://developer.android.com/topic/libraries/architecture/paging/) (for pagination and integration with room)
* [Epoxy](https://github.com/airbnb/epoxy) - Epoxy is a very flexible framework developed by Airbnb. We use it with recyclerView for any kind of data that can be presented in a list format (e.g. venue detail fragment)
* [Kodein](https://kodein.org) (KOtlin DEpendency INjection)
