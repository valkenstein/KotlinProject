//package utils
//
//import org.jetbrains.skia.Bitmap
//
//fun Context.generateQRCode(content: String): Bitmap {
//    // Определение размеров экрана устройства
//    val displayMetrics = resources.displayMetrics
//    val widthPixels = displayMetrics.widthPixels
//    val heightPixels = displayMetrics.heightPixels
//
//    // Определите 80% от минимального размера (ширина или высота)
//    val minDimension = min(widthPixels, heightPixels)
//    val qrSize = (minDimension * 0.8).toInt()  // 80% от минимального размера
//
//    val hints = HashMap<EncodeHintType, String>()
//    hints[EncodeHintType.CHARACTER_SET] = "UTF-8"
//
//    val bitMatrix: BitMatrix
//    try {
//        bitMatrix =
//            MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, qrSize, qrSize, hints)
//    } catch (Illegalargumentexception: IllegalArgumentException) {
//        return Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565)
//    }
//
//    val pixels = IntArray(qrSize * qrSize)
//    for (y in 0 until qrSize) {
//        val offset = y * qrSize
//        for (x in 0 until qrSize) {
//            pixels[offset + x] = if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE
//        }
//    }
//    val bitmap = Bitmap.createBitmap(qrSize, qrSize, Bitmap.Config.RGB_565)
//    bitmap.setPixels(pixels, 0, qrSize, 0, 0, qrSize, qrSize)
//    return bitmap
//}