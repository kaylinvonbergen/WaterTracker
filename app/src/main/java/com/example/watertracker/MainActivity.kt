package com.example.watertracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.watertracker.ui.theme.WaterTrackerTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember



private const val DEFAULT_GOAL_GLASSES = 8
private const val DEFAULT_START_GLASSES = 0



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WaterTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // entry point calling the top-level Stateful Composable
                    WaterTrackerApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


/**
 * STATEFUL PARENT COMPOSABLE
 * this composable owns the "source of truth" for the app's state.
 */
@Composable
fun WaterTrackerApp(modifier: Modifier = Modifier) {
    // requirement: Use remember { mutableStateOf(...) } to store state
    // glassesCount tracks the numerical progress
    var glassesCount by remember { mutableStateOf(DEFAULT_START_GLASSES) }

    // requirement: A second piece of state (Boolean) for the Switch
    var notificationsEnabled by remember { mutableStateOf(false) }

    // passing state down to the Stateless Screen (State Hoisting)
    WaterTrackerScreen(
        modifier = modifier,
        count = glassesCount,
        isNotificationsOn = notificationsEnabled, // pass it down
        onIncrement = { glassesCount++ }, // Lambda to update state
        onToggleNotifications = { notificationsEnabled = it } // Lambda to update state
    )
}


/**
 * STATELESS SCREEN COMPOSABLE
 * this handles the layout and coordinates child composables,
 * receives state via parameters and notifies parents via lambdas.
 */
@Composable
fun WaterTrackerScreen(
    count: Int,
    isNotificationsOn: Boolean,
    onIncrement: () -> Unit,
    onToggleNotifications: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    // logic calculated based on current state dynamic UI logic requirement)
    val isGoalReached = count >= DEFAULT_GOAL_GLASSES


    // Requirement: Use a Column or Row layout
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Requirement: Text displaying a title
        Text(
            text = "💧Water Tracker💧",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp)) // from 4.7

        // Requirement: Switch that changes state
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Reminders: ")
            Switch(
                checked = isNotificationsOn,
                onCheckedChange = onToggleNotifications
            )
        }

        Spacer(modifier = Modifier.height(24.dp))


        // Requirement: Custom stateless child
        // demonstrates State Hoisting: State goes down, events go up
        WaterCounterCard(
            title = "Today's Water Intake:",
            drankGlasses = count,
            goalGlasses = DEFAULT_GOAL_GLASSES,
            goalReached = isGoalReached,
            onAddGlass = onIncrement
        )

        Spacer(modifier = Modifier.height(16.dp))


        // checks if goal met or if there's more glasses to go
        val glassesRemaining = if (count >= DEFAULT_GOAL_GLASSES) {
            0
        } else {
            DEFAULT_GOAL_GLASSES - count
        }

        // Requirement: dynamic text updates automatically when state changes
        GoalStatusText(
            goalReached = isGoalReached,
            remainingGlasses = glassesRemaining

        )
    }

}

@Composable
fun WaterCounterCard(
    title: String, // app title << Water Tracker >>
    drankGlasses: Int,
    goalGlasses: Int,
    goalReached: Boolean,
    onAddGlass: () -> Unit, //lambda
    modifier: Modifier = Modifier
) {

     // TODO:
     // display dynamic text ( drankGlasses / goalGlasses)
     // button that calls onAddGlass()


    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title)
        Text(text = "Glasses: $drankGlasses / $DEFAULT_GOAL_GLASSES")

        Spacer(modifier = Modifier.height(18.dp))

        Button(onClick = onAddGlass) {
            Text(text = "+ Add Glass")
        }
    }
}


@Composable
fun GoalStatusText(
    goalReached: Boolean,
    remainingGlasses: Int,
    modifier: Modifier = Modifier)
    {

    //TODO, appears on goal reached ?
}

/**
 * COMPOSE PREVIEWS
 * used to verify the UI in Android Studio without running the app :D
 */
@Preview(showBackground = true)
@Composable
fun WaterCounterCardPreviewUnfulfilled() {

    // feed the stateless card "fake" data to see it in action
    // previewing with goal unachieved
    WaterCounterCard(
        title = "Preview (Not Done)",
        drankGlasses = 3,
        goalGlasses = 8,
        goalReached = false,
        onAddGlass = {} // Empty lambda for preview
    )

}

@Preview(showBackground = true)
@Composable
fun WaterCounterCardPreviewFulfilled() {
    // previewing with goal achieved
    WaterTrackerTheme {
        WaterCounterCard(
            title = "Preview (Goal Met!)",
            drankGlasses = 8,
            goalGlasses = 8,
            goalReached = true,
            onAddGlass = {}
        )
    }
}
