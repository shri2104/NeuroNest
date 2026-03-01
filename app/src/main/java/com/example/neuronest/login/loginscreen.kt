import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ub_logo_new_1_photoaidcom_cropped),
                    contentDescription = "Logo",
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Welcome Back",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(24.dp))

                InputField("Email", email, { email = it }, "Enter your email")
                InputField(
                    label = "Password",
                    value = password,
                    onValueChange = { password = it },
                    placeholder = "Enter password",
                    isPassword = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                errorMessage?.let {
                    Text(it, color = Color.Red)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (email.isNotBlank() && password.isNotBlank()) {
                            viewModel.signInWithEmailAndPassword(email, password) { success ->
                                if (success) {
                                    navController.navigate("DashBoard") {
                                        popUpTo("LoginScreen") { inclusive = true }
                                    }
                                } else {
                                    errorMessage = "Invalid email or password."
                                }
                            }
                        } else {
                            errorMessage = "Please fill all fields."
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Login")
                }

                Spacer(modifier = Modifier.height(12.dp))

                TextButton(onClick = onSignUp) {
                    Text("Don't have an account? Sign Up")
                }
            }
        }
    }
}

@Composable
fun SignUpScreen(
    viewModel: LoginScreenViewModel = viewModel(),
    navController: NavHostController
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var childName by remember { mutableStateOf("") }
    var caregiverName by remember { mutableStateOf("") }
    var caregiverPhone by remember { mutableStateOf("") }
    var userGroup by remember { mutableStateOf("") }
    var language by remember { mutableStateOf("") }
    var childAge by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF2F4F8))
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            item {

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "Create Account",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF3F51B5)
                )

                Text(
                    text = "Fill the details to continue",
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(24.dp))

                Card(
                    shape = RoundedCornerShape(24.dp),
                    elevation = CardDefaults.cardElevation(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        InputField("Child Name", childName, { childName = it }, "Enter child's name")
                        InputField("Caregiver Name", caregiverName, { caregiverName = it }, "Enter caregiver name")
                        InputField("Caregiver Phone", caregiverPhone, { caregiverPhone = it }, "Enter phone number")
                        InputField("Email", email, { email = it }, "Enter email")
                        Text("User Group",fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.DarkGray)
                        UserGroupDropdown(
                            selectedValue = userGroup,
                            onValueChange = { userGroup = it }
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        InputField("Language", language, { language = it }, "Enter language")
                        InputField("Child Age", childAge, { childAge = it }, "Enter child's age")

                        InputField(
                            label = "Password",
                            value = password,
                            onValueChange = { password = it },
                            placeholder = "Enter password",
                            isPassword = true
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        errorMessage?.let {
                            Text(it, color = Color.Red, fontSize = 13.sp)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {

                                if (
                                    email.isBlank() ||
                                    password.length < 6 ||
                                    childName.isBlank() ||
                                    caregiverName.isBlank() ||
                                    caregiverPhone.isBlank() ||
                                    userGroup.isBlank()
                                ) {
                                    errorMessage = "Please fill all fields correctly."
                                    return@Button
                                }

                                viewModel.createUserWithEmailAndPassword(
                                    email,
                                    password,
                                    childName,
                                    caregiverName,
                                    caregiverPhone,
                                    userGroup,
                                    language,
                                    childAge
                                ) { success ->

                                    if (success) {
                                        navController.navigate("Dashboard") {
                                            popUpTo("SignUp") { inclusive = true }
                                        }
                                    } else {
                                        errorMessage = "Registration failed. Try another email."
                                    }
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(52.dp),
                            shape = RoundedCornerShape(18.dp)
                        ) {
                            Text(
                                "Create Account",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}
@Composable
fun InputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Text(
            text = label,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(6.dp))

        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeholder) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (isPassword)
                PasswordVisualTransformation()
            else
                androidx.compose.ui.text.input.VisualTransformation.None,
            shape = RoundedCornerShape(14.dp),
            colors = TextFieldDefaults.colors(
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserGroupDropdown(
    selectedValue: String,
    onValueChange: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    val options = listOf("Child", "Caregiver")

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {

        OutlinedTextField(
            value = selectedValue,
            onValueChange = {},
            readOnly = true,
            label = { Text("Select User Group") },
            placeholder = { Text("Select user group") },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onValueChange(option)
                        expanded = false
                    }
                )
            }
        }
    }
}