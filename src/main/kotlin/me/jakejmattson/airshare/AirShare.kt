package me.jakejmattson.airshare

import io.javalin.Javalin
import io.javalin.util.FileUtil
import me.jakejmattson.airshare.managers.ClipboardManager
import me.jakejmattson.airshare.managers.TrayManager
import org.eclipse.jetty.http.HttpStatus
import java.io.InputStream

fun main(vararg args: String) {
    val port = args
        .firstOrNull()
        ?.toIntOrNull()
        ?: 6583

    val tray = TrayManager(port)
    val clipboard = ClipboardManager()

    Javalin.create()
        .post("/") {
            val type = it.contentType()!!
            println("Received $type from ${it.ip()}")

            when {
                type.startsWith("image") -> {
                    clipboard.saveImage(it.bodyInputStream())
                    tray.notify("Image saved to clipboard")
                }

                type.endsWith("json") -> {
                    val input = with(it.body()) { substring(indexOf(":") + 2, length - 2) }
                    clipboard.saveText(input)
                    tray.notify(input)
                }

                else -> {
                    tray.notify("Invalid POST")
                    it.status(HttpStatus.BAD_REQUEST_400)
                }
            }
        }
        .start(port)
}