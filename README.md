# 💧 Water Tracker App

A simple Android Studio app written in Kotlin that helps track your daily water intake.


### What makes Compose declarative?
Compose is declarative as it conceptually regenerate the entire screen from scratch when data changes, as opposed to a an imperative model, wherein developers act like a maunal middleman by capturing View objects and issuing explicit commands. 

In this app, we don't manually tell the UI to change, instead, we describe the UI as a function of its current data. When the `glassesCount` changes, Compose triggers **intelligent recomposition**, conceptually regenerating the screen to reflect the new state while skipping any components that haven't changed. This eliminates the "dual-system tax" of jumping between XML layouts and Kotlin logic, allowing the UI to stay perfectly in sync with the underlying data wihtout signficant synchronization logic.

### Where is state stored?
State is stored within the Composable lifecycle using the `remember` and `rememberSaveable` APIs. 

To avoid a fragmented source of truth (which is often common in XML Views), in our app, we use **State Hoisting**. The primary state for `glassesCount` and `notificationsEnabled` is hoisted to the top-level `WaterTrackerApp` Composable. 

We specifically use `rememberSaveable` so that the user's progress isn't lost during configuration changes, such as screen rotation. This ensures the UI is a constant reflection of the state, preventing "illegal states" where the data and the display might otherwise desync.


### AI statement 
This README was read over by Gemini, and some grammatical and stylistic changes were accepted. 

Anna's portions of the code were also inserted into Gemini as a "third" pair of eyes, to ensure coding conventions were upheld and nothing was done in a blatantly suboptimal way. 