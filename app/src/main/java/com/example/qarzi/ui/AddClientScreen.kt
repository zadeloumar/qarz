package com.example.qarzi.ui

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.net.toFile
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import androidx.compose.foundation.text.KeyboardOptions

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.qarzi.viewmodel.AddClientViewModel


@Composable
fun AddClientScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var debt by remember { mutableStateOf("") }
    var returnDate by remember { mutableStateOf("") }
    var passportPhotoUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current

    // Открытие галереи для выбора фото
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        passportPhotoUri = uri
    }

    val viewModel: AddClientViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Добавление клиента", style = MaterialTheme.typography.headlineSmall)

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Имя клиента") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Телефон") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Адрес") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = debt,
            onValueChange = { debt = it },
            label = { Text("Сумма долга") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = returnDate,
            onValueChange = { returnDate = it },
            label = { Text("Дата возврата (например, 2025-07-01)") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(onClick = { launcher.launch("image/*") }) {
            Text("📸 Прикрепить фото паспорта (опц.)")
        }

        passportPhotoUri?.let { uri ->
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }

        Button(
            onClick = {
                if (name.isBlank() || phone.isBlank()) {
                    Toast.makeText(context, "Имя и телефон обязательны", Toast.LENGTH_SHORT).show()
                } else {
                    val client = Client(
                        name = name,
                        phone = phone,
                        address = address,
                        debt = debt.toDoubleOrNull() ?: 0.0,
                        returnDate = returnDate,
                        passportPhotoUri = passportPhotoUri?.toString(),
                        storeLogin = "store1" // сюда передадим логин магазина позже
                    )
                    viewModel.addClient(client) {
                        Toast.makeText(context, "Клиент сохранён", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("✅ Сохранить")
        }

    }
}
