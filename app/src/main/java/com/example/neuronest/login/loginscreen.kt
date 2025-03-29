import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.neuronest.R

@Composable
fun LoginScreen(
    onSignUp: () -> Unit,
    navController: NavHostController,
    viewModel: LoginScreenViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Image(
                painter = painterResource(id = R.drawable.ub_logo_new_1_photoaidcom_cropped), // Replace with your logo
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(120.dp)
                    .padding(bottom = 16.dp)
            )


            InputField(label = "Email", value = email, onValueChange = { email = it }, placeholder = "Enter your email")
            Spacer(modifier = Modifier.height(8.dp))
            InputField(label = "Password", value = password, onValueChange = { password = it }, placeholder = "Enter your password")

            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage != null) {
                Text(
                    text = errorMessage!!,
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            Button(
                onClick = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        viewModel.signInWithEmailAndPassword(email, password) { success ->
                            if (success) {
                                navController.navigate("DashBoard") {
                                    popUpTo("LoginScreen") { inclusive = true }
                                }
                            } else {
                                errorMessage = "Invalid email or password. Please try again."
                            }
                        }
                    } else {
                        errorMessage = "Please fill in all fields."
                    }
                }
            ) {
                Text("Login")
            }
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = onSignUp) {
                Text("Don't have an account? Sign Up")
            }
        }
    }
}

@Composable
fun SignUpScreen(viewModel: LoginScreenViewModel = viewModel(), navController: NavHostController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var childName by remember { mutableStateOf("") }
    var caregiverName by remember { mutableStateOf("") }
    var caregiverPhone by remember { mutableStateOf("") }
    var userGroup by remember { mutableStateOf("") }
    var language by remember { mutableStateOf("") }
    var childAge by remember { mutableStateOf("") }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 32.dp, bottom = 32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text("SIGN UP", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(10.dp))

            Divider(Modifier.height(4.dp))

            Spacer(modifier = Modifier.height(10.dp))

            InputField(label = "Child Name", value = childName, onValueChange = { childName = it }, placeholder = "Enter child's name")
            InputField(label = "Caregiver's Name", value = caregiverName, onValueChange = { caregiverName = it }, placeholder = "Enter caregiver's name")
            InputField(label = "Caregiver's Phone No.", value = caregiverPhone, onValueChange = { caregiverPhone = it }, placeholder = "Enter phone number")
            InputField(label = "Email ID", value = email, onValueChange = { email = it }, placeholder = "Enter email")
            InputField(label = "User Group", value = userGroup, onValueChange = { userGroup = it }, placeholder = "Enter user group")
            InputField(label = "Language", value = language, onValueChange = { language = it }, placeholder = "Enter language")
            InputField(label = "Child Age", value = childAge, onValueChange = { childAge = it }, placeholder = "Enter child's age")
            InputField(label = "Password", value = password, onValueChange = { password = it }, placeholder = "Enter password")

            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                viewModel.createUserWithEmailAndPassword(email, password) { success ->
                    if (success) {
                        Log.d("SignUp", "Navigating to Dashboard")
                        navController.navigate("DashBoard") {
                            popUpTo("SignUpScreen") { inclusive = true }
                        }
                    } else {
                        Log.e("SignUp", "Registration failed, staying on SignUpScreen")
                    }
                }
            }) {
                Text("Create Account")
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}





@Composable
fun InputField(label: String, value: String, onValueChange: (String) -> Unit, placeholder: String) {
    Text(
        text = label,
        fontSize = 16.sp,
        color = Color.Black,
        modifier = Modifier.padding(bottom = 4.dp)
    )
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder) },
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                cursorColor = Color.Blue,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
    }
}