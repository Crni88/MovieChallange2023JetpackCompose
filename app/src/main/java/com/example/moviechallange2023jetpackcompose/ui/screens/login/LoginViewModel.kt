package com.example.moviechallange2023jetpackcompose.ui.screens.login

import androidx.lifecycle.ViewModel
import com.example.moviechallange2023jetpackcompose.data.IUserRepository
import com.example.moviechallange2023jetpackcompose.domain.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

data class LoginState(
    val name: String = "",
    val emailAddress: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isUserSignedIn: Boolean = false
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: IUserRepository
) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun updateState(
        name: String = loginState.value.name,
        email: String = loginState.value.emailAddress,
        password: String = loginState.value.password,
        confirmPassword: String = loginState.value.confirmPassword,
        isUserSignedIn: Boolean = loginState.value.isUserSignedIn
    ) {
        _loginState.value = loginState.value.copy(
            name = name,
            emailAddress = email,
            password = password,
            confirmPassword = confirmPassword,
            isUserSignedIn = isUserSignedIn
        )
    }

    fun loginUser() {
        userRepository.saveUser(
            User(
                name = loginState.value.name,
                email = loginState.value.emailAddress
            )
        )
        updateState(isUserSignedIn = true)
    }
}