package com.example.qarzi.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Меню действий",
            style = MaterialTheme.typography.headlineSmall
        )

        Button(
            onClick = { navController.navigate("addClient") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("➕ Добавить нового клиента")
        }

        Button(
            onClick = { /* TODO: Перейти на экран добавления долга */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("💰 Добавить долг клиенту")
        }

        Button(
            onClick = { /* TODO: Перейти на экран списка долгов */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("📋 Список клиентов и долгов")
        }

        Button(
            onClick = { /* TODO: Перейти на экран удаления долгов */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("❌ Удалить долг")
        }

        Button(
            onClick = { /* TODO: Перейти на экран чёрного списка */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("⚠️ Чёрный список")
        }
    }
}
