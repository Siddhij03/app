package com.example.util

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri

object AppShareConfig {
    /**
     * The live public URL where anyone can open and use the NutriPulse app
     * directly in their mobile or desktop browser with zero install required.
     */
    const val SHARED_APP_URL = "https://ais-pre-lvv5enkatg76ixxbbpze7i-371484522525.asia-southeast1.run.app"
    const val APP_NAME = "NutriPulse"

    fun getShareMessage(userName: String): String {
        val cleanName = if (userName.isBlank()) "a friend" else userName
        return """
⚡ You're invited to NutriPulse!

I'm using NutriPulse to forecast daily energy curves, beat afternoon slumps, and eliminate kitchen food waste with AI.

👉 Open and use the app instantly in your browser (no install needed):
$SHARED_APP_URL

Shared by $cleanName
        """.trimIndent()
    }

    fun shareApp(context: Context, userName: String) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(
                Intent.EXTRA_SUBJECT,
                "Try NutriPulse - Circadian Energy & Smart Kitchen AI"
            )
            putExtra(
                Intent.EXTRA_TEXT,
                getShareMessage(userName)
            )
        }
        val chooser = Intent.createChooser(shareIntent, "Share NutriPulse App Link")
        chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(chooser)
    }

    fun copyLinkToClipboard(context: Context): Boolean {
        return try {
            val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("NutriPulse App Link", SHARED_APP_URL)
            clipboard.setPrimaryClip(clip)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun openInBrowser(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(SHARED_APP_URL)).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        } catch (_: Exception) {
        }
    }
}
