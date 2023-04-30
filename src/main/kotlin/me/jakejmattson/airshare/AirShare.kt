package me.jakejmattson.airshare

import io.javalin.Javalin
import io.javalin.http.HttpStatus
import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon
import java.awt.datatransfer.StringSelection
import java.net.InetAddress

fun main(vararg args: String) {
    val port = args
        .firstOrNull()
        ?.toIntOrNull()
        ?: 6583

    val image = Toolkit.getDefaultToolkit().createImage(Javalin::class.java.getResource("/airshare.png"))
    val tooltip = "AirShare Client\n${InetAddress.getLocalHost().hostAddress}:$port"
    val trayIcon = TrayIcon(image, tooltip).apply { isImageAutoSize = true }

    SystemTray.getSystemTray().add(trayIcon)

    Javalin.create()
        .post("/") {
            val input = with(it.body()) { substring(indexOf(":") + 2, length - 2) }

            Toolkit.getDefaultToolkit().systemClipboard.setContents(StringSelection(input), null)
            trayIcon.displayMessage(null, input, TrayIcon.MessageType.INFO)
            it.status(HttpStatus.OK)
        }
        .start(port)
}