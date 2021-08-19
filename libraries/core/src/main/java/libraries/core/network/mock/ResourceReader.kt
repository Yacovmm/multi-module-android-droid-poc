package libraries.core.network.mock

import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

object ResourceReader {

    fun readFileWithoutNewLineFromResources(jsonIs: InputStream): String {
        try {
            val builder = StringBuilder()
            val reader = BufferedReader(InputStreamReader(jsonIs))

            var str: String? = reader.readLine()
            while (str != null) {
                builder.append(str)
                str = reader.readLine()
            }
            return builder.toString()
        } catch (e: Exception) {
            println(e.message)
            return ""
        } finally {
            jsonIs.close()
        }
    }

}