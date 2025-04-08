package utils

import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object JsonUtil {
    val json = Json { prettyPrint = true; ignoreUnknownKeys = true }

    inline fun <reified T> toJson(data: T): String {
        return json.encodeToString(data)
    }

    inline fun <reified T> fromJson(jsonString: String): T {
        return json.decodeFromString(jsonString)
    }
}