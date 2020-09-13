# README #

### What is this repository for? ###
* This is a sample App demonstrating Git Trending repositories.
* First page displays "list of Trending Git repositories"
* Onclick of any item will expand the view to display repo details
* Version
1.0

### How do I get set up? ###
1. Open the project in Android Studio
2. Initiate Build
3. Push the binary on to Any Android Device
4. Launch the App to see the result

### Architecture guidelines ###
1. App is developed using Kotlin language
1. Application is designed and implemented with MVVM Architecture style + Repository
2. Application is structured into below high level packages

* common -> contains Extensions helper classe
* db -> contains database and Shared preference classes
* network -> contains Retrofit client and other network related classes
* repository -> contains repository manager with model classes
* ui -> contains view related classes (Activity, Fragment, Adapter)
* viewmodel [ViewModel] ->contains viewmodel related classes (GitRepoViewModel)

### Libraries/Components used ###
1. Retrofit for networking
2. androidx.*
3. Picasso for image download
4. Room database for local storage
5. Few other libraries for extension functions

###### Unit testing #######
1. network -> contains Git Repo service test class. Covered fully
2. viewmodel -> partially covered test cases. May not succeed. Added to demostrate the structure of view model classes testing
