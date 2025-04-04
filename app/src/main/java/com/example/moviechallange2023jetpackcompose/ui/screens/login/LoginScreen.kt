package com.example.moviechallange2023jetpackcompose.ui.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.moviechallange2023jetpackcompose.R
import com.example.moviechallange2023jetpackcompose.navigation.Routes.DASHBOARD
import com.example.moviechallange2023jetpackcompose.ui.components.MovieTextField
import com.example.moviechallange2023jetpackcompose.ui.theme.MovieShapes

@Composable
fun LoginScreenRoute(
    loginViewModel: LoginViewModel = hiltViewModel(),
    navController: NavHostController
) {
    val loginState by loginViewModel.loginState.collectAsState()

    if (loginState.isUserSignedIn) {
        loginViewModel.updateState(isUserSignedIn = false)
        navController.navigate(DASHBOARD)
    }

    LoginScreen(
        state = loginState,
        onNameChanged = { loginViewModel.updateState(name = it) },
        onEmailChanged = { loginViewModel.updateState(email = it) },
        onPasswordChanged = { loginViewModel.updateState(password = it) },
        onPasswordConfirmChanged = { loginViewModel.updateState(confirmPassword = it) },
        loginUser = { loginViewModel.loginUser() }
    )
}

@Composable
fun LoginScreen(
    state: LoginState,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onPasswordConfirmChanged: (String) -> Unit,
    loginUser: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            contentAlignment = Alignment.BottomCenter,
            content = {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio((16 / 9).toFloat()),
                    painter = painterResource(R.drawable.poster_grid),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Image(
                    modifier = Modifier
                        .size(160.dp),
                    painter = painterResource(R.drawable.group),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
            }
        )
        Column(modifier = Modifier.padding(30.dp)) {
            MovieTextField(
                value = state.name,
                onValueChange = { onNameChanged(it) },
                label = "Name*",
                imeAction = ImeAction.Next,
                errorMessage = ""
            )
            MovieTextField(
                value = state.emailAddress,
                onValueChange = { onEmailChanged(it) },
                label = "Email Address*",
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email,
                errorMessage = "",
            )
            MovieTextField(
                value = state.password,
                onValueChange = { onPasswordChanged(it) },
                label = "Password*",
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password,
                errorMessage = "",
                isPassword = true
            )
            MovieTextField(
                value = state.confirmPassword,
                onValueChange = { onPasswordConfirmChanged(it) },
                label = "Confirm Password*",
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password,
                errorMessage = "",
                isPassword = true
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = MovieShapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                onClick = { loginUser() }) {
                Text(
                    text = "Sign Up",
                    color = Color.White,
                    style = MaterialTheme.typography.titleSmall
                )
            }
        }
    }
}