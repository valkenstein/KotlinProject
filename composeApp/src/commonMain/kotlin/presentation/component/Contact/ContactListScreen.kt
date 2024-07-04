package presentation.component.Contact

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import domain.model.ContactDom
import org.koin.compose.viewmodel.koinViewModel
import presentation.ContactListEvent
import presentation.ContactListState
import presentation.mvvm.ContactListViewModel


@Composable
fun initContact(onClick: (() -> Unit)? = null) {
    val viewModel: ContactListViewModel = koinViewModel()
    val state by viewModel.state.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    )
    {
        ContactListScreen(
            state = state,
            newConcat = viewModel.newContact,
            onEvent = viewModel::onEvent,
            onClick = onClick
        )
    }
}

@Composable
fun ContactListScreen(
    state: ContactListState,
    newConcat: ContactDom?,
    onEvent: (ContactListEvent) -> Unit,
    onClick: (() -> Unit)? = null,
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onClick?.invoke()
                    onEvent.invoke(ContactListEvent.OnAddNewContactClick)
                },
                shape = RoundedCornerShape(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = null
                )
            }
        }

    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = state.contacts.size.toString(),
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            items(state.contacts) { contact ->
                ContactListItem(
                    contactDom = contact,
                    modifier = Modifier.fillMaxSize().background(Color.White).clickable {
                        onEvent.invoke(ContactListEvent.SelectContact(contact))
                    }.padding(horizontal = 16.dp)
                )
            }
        }
    }
}