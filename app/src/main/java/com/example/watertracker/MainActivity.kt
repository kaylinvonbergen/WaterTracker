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



private const val DEFAULT_GOAL_GLASSES = 8
private const val DEFAULT_START_GLASSES = 0



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WaterTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WaterTrackerApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun WaterTrackerApp(modifier: Modifier = Modifier) {
    // TODO: stateful screen composable


    WaterTrackerScreen(modifier = modifier)
}


@Composable
fun WaterTrackerScreen(modifier: Modifier = Modifier) {
    /*
     TODO:
     - state to remember glasses drank
     - lambda callback for adding a glass
     */

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "💧Water Tracker💧",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        WaterCounterCard(
            title = "Today's Water Intake:",
            drankGlasses = 0,          // TODO
            goalGlasses = 0,           // TODO
            goalReached = false,       // TODO
            onAddGlass = { /* TODO */ } //lambda 🙂‍↕️
        )
    }

}

// UI
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


@Preview(showBackground = true)
@Composable
fun WaterCounterCardPreviewUnfulfilled() {
    //TODO
}

@Preview(showBackground = true)
@Composable
fun WaterCounterCardPreviewFulfilled() {
    //TODO
}
