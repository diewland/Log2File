package com.diewland.log2file

import android.util.Log
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class Log2File (
    private val path: String = "/sdcard/log.txt",
    private val limit: Int = 100,
    private val ts: Boolean = true
) {
    // config
    private val tag = "LOG2FILE"
    private val eol = "\r\n"

    // mount file
    private val file = File(path)

    // make sure file was created
    init {
        val msg = when (file.createNewFile()) {
            true -> "create $path"
            false -> "$path already exists"
        }
        Log.d(tag, msg)
    }

    // write
    fun write (msg: String) {
        // Log.d(tag, "|<- $msg")

        // timestamp
        val m = when(ts) {
            true -> getTs() + " $msg"
            false -> msg
        }

        // get lines
        val lines =  file.readLines()
        val size = lines.size

        // add to file
        if (size < limit) {
            file.appendText(m + eol)
        }
        else { // reach limit
            val n = size - limit + 1
            val ll = ArrayList(lines.drop(n))
            ll.add(m)
            file.writeText(ll.joinToString(eol) + eol)
        }
    }

    // read
    fun read (): List<String> {
        return readLines()
    }
    fun readText (): String {
        return file.readText()
    }
    fun readLines (): List<String> {
        val lines =  file.readLines()
        Log.d(tag, "|-> read ${lines.size} lines")
        return lines
    }

    private fun getTs (): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sdf.format(Date())
    }

}
