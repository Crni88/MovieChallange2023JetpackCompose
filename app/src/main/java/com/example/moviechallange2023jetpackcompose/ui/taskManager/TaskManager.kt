package com.example.moviechallange2023jetpackcompose.ui.taskManager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.moviechallange2023jetpackcompose.domain.Ticket
import com.example.moviechallange2023jetpackcompose.ui.theme.MovieChallange2023JetpackComposeTheme

@Composable
fun TaskManagerRoot(
    viewModel: TaskManagerViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    TaskManagerScreen(
        state = state,
        taskovi = viewModel.data,
        onAction = viewModel::onAction,
        createNewTask = viewModel::postTask
    )
}
@Composable
fun TaskManagerScreen(
    state: TaskManagerState,
    onAction: (TaskManagerAction) -> Unit,
    taskovi: State<List<Ticket>>,
    createNewTask: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(55.dp))
        Button(onClick = { createNewTask() }) {
            Text("Create New Task")
        }
        Spacer(modifier = Modifier.height(25.dp))
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(taskovi.value) { ticket ->
                TicketCard(ticket = ticket)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun TicketCard(ticket: Ticket, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Ticket #${ticket.id}", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Status: ${ticket.status}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "What: ${ticket.what}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Where: ${ticket.where}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Who: ${ticket.who}", style = MaterialTheme.typography.bodyMedium)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Planned Completion: ${ticket.plannedCompletionDate}", style = MaterialTheme.typography.bodySmall)
            Text(text = "Actual Completion: ${ticket.actual_completion_date}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MovieChallange2023JetpackComposeTheme {
        TaskManagerScreen(
            state = TaskManagerState(),
            onAction = {},
            taskovi = emptyList<Ticket>() as State<List<Ticket>>,
            createNewTask = {}
        )
    }
}