package com.diewland.log2file

import android.os.Handler
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

object DailyLog {

    // config
    private const val eol = "\r\n"

    // thread
    private val handler = Handler()
    fun release() { handler.removeCallbacksAndMessages(null) }

    fun write (
        text: String,
        folderPath: String = "/sdcard",
        filePrefix: String = "daily",
        fileExt: String = "log",
        yyyymmdd: String? = null,
    ) {
        handler.post {
            append(text, folderPath, filePrefix, fileExt, yyyymmdd)
        }
    }

    fun clean (
        limit: Int = 10, // days
        folderPath: String = "/sdcard",
        filePrefix: String = "daily",
        fileExt: String = "log",
    ) {
        // sort files in folder from old to new
        val dir = File(folderPath)
        val logs = dir.listFiles { file ->
            file.name.startsWith(filePrefix) && file.name.endsWith(fileExt)
        } ?: arrayOf()
        logs.sortBy { it.name }

        // remove old files
        val remove = logs.size - limit
        if (remove > 0)
            logs.take(remove).forEach { it.delete() }
    }

    // - - - - - X - - - - -

    private fun append (
        text: String,
        folderPath: String,
        filePrefix: String,
        fileExt: String,
        yyyymmdd: String?,
    ) {
        // get timestamp
        val ts = getTs()
        val ymd = yyyymmdd ?: ts.split(" ")[0].replace("-", "")

        // create folder (if need)
        val dir = File(folderPath)
        dir.mkdirs()

        // create file (if need)
        val filename = "${filePrefix}_$ymd.$fileExt"
        val file = File(dir, filename)
        file.createNewFile()

        // append text
        file.appendText("$ts $text$eol")
    }

    private fun getTs (): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(Date())
    }

}