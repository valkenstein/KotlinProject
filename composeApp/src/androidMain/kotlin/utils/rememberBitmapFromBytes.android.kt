package utils

import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

@Composable
actual fun rememberBitmapFromBytes(byte: ByteArray?): ImageBitmap? {
    return remember(byte) {
        if (byte != null) {
            BitmapFactory.decodeByteArray(byte,0, byte.size).asImageBitmap()
        } else null
    }
}