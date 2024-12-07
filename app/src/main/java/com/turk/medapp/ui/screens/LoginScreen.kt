package com.turk.medapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.turk.medapp.R
import com.turk.medapp.ui.AppState
import com.turk.medapp.ui.main.MainViewModel
import com.turk.medapp.ui.rememberAppState
import com.turk.medapp.ui.theme.MyApplicationTheme
import com.turk.medapp.ui.theme.SmallTitle
import com.turk.medapp.ui.theme.mediumUnit
import com.turk.medapp.ui.theme.smallUnit

@Composable
fun LoginScreen(
    mainViewModel: MainViewModel,
    appState: AppState,
    moveToMedicineListScreen: () -> Unit,
) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val errorMessage = stringResource(R.string.emptyCredentialsError)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.background), contentScale = ContentScale.FillWidth
            )
            .padding(horizontal = mediumUnit, vertical = smallUnit),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.background(color = Color.Transparent)
        ) {
            Card(
                modifier = Modifier.background(
                    color = Color.White,
                    shape = RoundedCornerShape(12.dp)
                )
            ) {

                Column {

                    TextField(
                        value = userName,
                        placeholder = { SmallTitle(text = "Enter user name") },
                        colors = TextFieldDefaults.colors(
                            focusedPlaceholderColor = Color.Black, // Hint text color when focused
                            unfocusedPlaceholderColor = Color.Gray // Hint text color when unfocused
                        ),
                        onValueChange = {
                            userName = it

                        })

                    TextField(
                        value = password,
                        placeholder = { SmallTitle(text = "Enter your password") },
                        colors = TextFieldDefaults.colors(
                            focusedPlaceholderColor = Color.Black, // Hint text color when focused
                            unfocusedPlaceholderColor = Color.Gray // Hint text color when unfocused
                        ),
                        visualTransformation = PasswordVisualTransformation(),
                        onValueChange = {
                            password = it
                        })

                    Button(
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .padding(10.dp)
                            .align(alignment = Alignment.CenterHorizontally),
                        onClick = {
                            if (userName.isNotBlank() && password.isNotBlank()) {
                                mainViewModel.saveUserName(userName)
                                moveToMedicineListScreen()
                            } else {

                                appState.showSnackbar(errorMessage)
                            }
                        }
                    ) {
                        SmallTitle(
                            text = stringResource(R.string.login),
                            color = Color.White
                        )
                    }
                }


            }


        }


    }

}


@Composable
@Preview
fun PreviewLoginScreen() {
    MyApplicationTheme {
        LoginScreen(hiltViewModel(), rememberAppState()) {}
    }

}