package com.diewland.log2file

object DailyLog {

    fun append (
        text: String,
        folderPath: String = "/sdcard",
        filePrefix: String = "daily",
        fileExt: String = "log",
        yyyymmdd: String? = null,
    ) {
        // create folder (if need)
        // TODO

        // mount file (create if need)
        // TODO

        // append text
        // TODO

        // close file
        // TODO
    }

    fun clean (
        limit: Int = 10, // days
        folderPath: String = "/sdcard",
        filePrefix: String = "daily",
        fileExt: String = "log",
    ) {
        // sort files in folder from old to new
        // TODO

        // remove old files ( all - limit )
        // TODO
    }

    // TODO [?] add thread to remove delay

}