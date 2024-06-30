package presentation.component.Contact

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import domain.model.ContactDom
import utils.rememberBitmapFromBytes

@Composable
fun ContactPhoto(
    contact: ContactDom?,
    modifier: Modifier,
    iconSize: Dp = 25.dp
) {
    val photo = rememberBitmapFromBytes(contact?.image)
    val photoModifier = modifier.clip(RoundedCornerShape(35.dp))
    if (photo != null) {
        Image(
            modifier = photoModifier,
            bitmap = photo,
            contentDescription = contact?.firstName,
            contentScale = ContentScale.Crop
        )
    } else {
        Box(
            modifier = photoModifier.background(MaterialTheme.colorScheme.secondaryContainer),
        ) {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = contact?.firstName,
                modifier = Modifier.size(iconSize).align(Alignment.Center),
                tint = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}