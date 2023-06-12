package me.jakejmattson.airshare.managers

import java.awt.Image
import java.awt.Toolkit
import java.awt.datatransfer.DataFlavor
import java.awt.datatransfer.StringSelection
import java.awt.datatransfer.Transferable
import java.awt.datatransfer.UnsupportedFlavorException
import java.io.IOException
import java.io.InputStream
import javax.imageio.ImageIO

private val clipboard = Toolkit.getDefaultToolkit().systemClipboard

class ClipboardManager {
    fun saveText(text: String) {
        clipboard.setContents(StringSelection(text), null)
    }

    fun saveImage(image: InputStream) {
        clipboard.setContents(ImageSelection(ImageIO.read(image)), null)
    }
}

private class ImageSelection(private val image: Image) : Transferable {
    override fun getTransferDataFlavors() = arrayOf(DataFlavor.imageFlavor)
    override fun isDataFlavorSupported(flavor: DataFlavor) = flavor.equals(DataFlavor.imageFlavor)

    @Throws(UnsupportedFlavorException::class, IOException::class)
    override fun getTransferData(flavor: DataFlavor) =
        if (flavor.equals(DataFlavor.imageFlavor)) {
            image
        } else {
            throw UnsupportedFlavorException(flavor)
        }
}