package com.example.moviechallange2023jetpackcompose.ui.taskManager

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviechallange2023jetpackcompose.data.ITaskManagerRepository
import com.example.moviechallange2023jetpackcompose.domain.Ticket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskManagerViewModel @Inject constructor(
    private val repository: ITaskManagerRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(TaskManagerState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                /** Load initial data here **/
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = TaskManagerState()
        )

    fun onAction(action: TaskManagerAction) {
        when (action) {
            else -> TODO("Handle actions")
        }
    }

    private val _data = mutableStateOf<List<Ticket>>(emptyList())
    val data: State<List<Ticket>> get() = _data

    private val _isLoading = mutableStateOf(true)
    val isLoading: State<Boolean> get() = _isLoading

    init {
        fetchDataFromApi()
    }

    fun postTask() {
        val ticket = Ticket(
            actual_completion_date = "2025-03-20",
            comments = listOf("Fixed issue", "Client approved"),
            created_at = 1708905600L,
            guide_id = 101,
            how = "Repaired the system",
            id = 1,
            media = listOf("image1.jpg", "image2.png"),
            plannedCompletionDate = "2025-03-18",
            score = 85,
            status = "Completed",
            what = "Fix server outage",
            `when` = "2025-03-15",
            `where` = "Data Center 1",
            who = "John Doe"
        )
        viewModelScope.launch {
            try {
                val response = repository.postTask(ticket)
                if (response.isSuccessful) {
                    fetchDataFromApi()
                }
            } catch (e: Exception) {

            }
        }
    }

    private fun fetchDataFromApi() {
        viewModelScope.launch {
            try {
                val response = repository.getTasks(isStaffPick = true)
                if (response.isSuccessful) {
                    _data.value = response.body() ?: emptyList()
                } else {

                }
            } catch (e: Exception) {

            } finally {
                _isLoading.value = false
            }
        }
    }
}