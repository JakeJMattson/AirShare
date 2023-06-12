package me.jakejmattson.airshare.managers

import io.javalin.Javalin
import java.awt.SystemTray
import java.awt.Toolkit
import java.awt.TrayIcon
import java.net.InetAddress

class TrayManager(port: Int) {
    private val trayIcon: TrayIcon

    init {
        val image = Toolkit.getDefaultToolkit().createImage(Javalin::class.java.getResource("/airshare.png"))
        val tooltip = "AirShare Client\n${InetAddress.getLocalHost().hostAddress}:$port"

        trayIcon = TrayIcon(image, tooltip).apply { isImageAutoSize = true }

        SystemTray.getSystemTray().add(trayIcon)
    }

    fun notify(message: String) {
        trayIcon.displayMessage(null, message, TrayIcon.MessageType.INFO)
    }
}