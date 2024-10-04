package com.vahid.swipetoreveal

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ContactsScreen() {
    val context = LocalContext.current
    val contacts = remember {
        mutableStateListOf(
            *(0..100).map {
                Contactui(
                    id = it,
                    name = "Contact $it",
                    isOptionsRevealed = false
                )
            }.toTypedArray()
        )
    }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(items = contacts, key = { _, contact -> contact.id }) { index, contact ->
            SwipeableIconWithActions(isRevealed = contact.isOptionsRevealed,
                onExpanded = { contacts[index] = contact.copy(isOptionsRevealed = true)},
                onCollapsed = { contacts[index] = contact.copy(isOptionsRevealed = false)},
                actions = {
                    ActionIcon(
                        onClick = { contacts.remove(contact) },
                        backgroundColor = Color.Red,
                        icon = Icons.Default.Delete,
                        modifier = Modifier.fillMaxHeight()
                    )
                    ActionIcon(
                        onClick = { contacts[index] = contact.copy(isOptionsRevealed = false) },
                        backgroundColor = Color.Yellow,
                        icon = Icons.Default.Email,
                        modifier = Modifier.fillMaxHeight()
                    )
                    ActionIcon(
                        onClick = { contacts[index] = contact.copy(isOptionsRevealed = false) },
                        backgroundColor = Color.Green,
                        icon = Icons.Default.Share
                    )
                }) {
                Text(
                    text = "Contacts ${contact.id}",
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}