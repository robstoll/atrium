package ch.tutteli.atrium.logic.creating.typeutils.impl

import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.creating.typeutils.MapLikeToIterablePairTransformer
import ch.tutteli.atrium.logic.utils.iterableLikeToIterable
import ch.tutteli.kbox.appendToStringBuilder

/**
 * Transforms all [Map] as well as all [IterableLike] which have a resulting element type of `[Pair]<K, V>` to an
 * [Iterable] with an element type `[Pair]<K, V>`.
 */
class DefaultMapLikeToIterablePairTransformer(private val container: AssertionContainer<*>) : MapLikeToIterablePairTransformer {

    override fun <K, V> unsafeTransform(mapLike: IterableLike): List<Pair<K, V>> {

        val iAny: List<Pair<*, *>> = when (mapLike) {
            is Map<*, *> -> mapLike.entries.map { it.key to it.value }
            else -> try {
                container.iterableLikeToIterable<Any?>(mapLike).map { it as Pair<*, *> }
            } catch (cause: IllegalArgumentException) {
                throw IllegalArgumentException(
                    StringBuilder("MapLikeToMapTransformer accepts arguments of types: ").also { sb ->
                        supportedTypes().appendToStringBuilder(sb, ", ", " and ") { sb.append(it) }
                    }
                        .append(" as well as IterableLike with an element type `Pair<K, V>` -- see `cause` why the IterableLike transformation failed.")
                        .toString(), cause
                )
            }
        }

        @Suppress(
            // We push the responsibility to define a correct `K` and `V` to the caller and therefore it is OK
            // because we don't change the entries of MapLike above
            "UNCHECKED_CAST"
        )
        return iAny as List<Pair<K, V>>
    }

    override fun supportedTypes(): List<String> = listOf("Map")
}
