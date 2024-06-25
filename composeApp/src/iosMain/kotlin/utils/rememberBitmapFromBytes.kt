package utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asComposeImageBitmap
import org.jetbrains.skia.Bitmap
import org.jetbrains.skia.Image

@Composable
actual fun rememberBitmapFromBytes(byte: ByteArray?): ImageBitmap? {
    return remember(byte) {
        if (byte != null) {
            Bitmap.makeFromImage(
                Image.makeFromEncoded(byte)
            ).asComposeImageBitmap()
        } else null
    }
}