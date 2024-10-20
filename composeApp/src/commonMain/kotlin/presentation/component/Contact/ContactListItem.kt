package presentation.component.Contact

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import domain.model.ContactDom

@Composable
fun ContactListItem(
    contactDom: ContactDom,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ContactPhoto(
            contact = contactDom,
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(16.dp))

        Text(
            text = contactDom.firstName,
            Modifier.weight(1f)
        )

    }
}