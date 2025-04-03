package utils

import java.io.BufferedReader
import java.io.FileReader
import java.util.*

object FileUtils {
    fun readCSV(filePath: String): List<List<String>> {
        val records: MutableList<List<String>> = ArrayList()
        val br = BufferedReader(FileReader(filePath))

        var line = br.readLine()
        while (line != null) {
            val values = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            records.add(listOf(*values))
            line = br.readLine()
        }

        br.close()
        return records
    }
}
