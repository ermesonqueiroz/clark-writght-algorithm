// src/main/kotlin/serializers/LinkedListSerializer.kt
package serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*
import kotlinx.serialization.builtins.ListSerializer
import java.util.LinkedList

open class LinkedListSerializer<T>(private val dataSerializer: KSerializer<T>) : KSerializer<LinkedList<T>> {
    override val descriptor: SerialDescriptor =
        ListSerializer(dataSerializer).descriptor

    override fun serialize(encoder: Encoder, value: LinkedList<T>) {
        encoder.encodeSerializableValue(ListSerializer(dataSerializer), value.toList())
    }

    override fun deserialize(decoder: Decoder): LinkedList<T> {
        val list = decoder.decodeSerializableValue(ListSerializer(dataSerializer))
        return LinkedList(list)
    }
}