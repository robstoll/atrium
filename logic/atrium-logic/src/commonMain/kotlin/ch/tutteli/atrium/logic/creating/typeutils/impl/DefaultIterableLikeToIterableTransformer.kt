//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.typeutils.impl

import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.logic.creating.typeutils.IterableLike
import ch.tutteli.atrium.logic.creating.typeutils.IterableLikeToIterableTransformer
import ch.tutteli.kbox.appendToStringBuilder

/**
 * Transforms [Sequence] and all [Array] types to [Iterable].
 *
 * following a more precise list:
 * - Iterable (does not transform anything)
 * - Sequence
 * - Array
 * - CharArray
 * - ByteArray
 * - ShortArray
 * - IntArray
 * - LongArray
 * - FloatArray
 * - DoubleArray
 * - BooleanArray
 */
@Deprecated(
    "Use the import from atrium-core, atrium-logic will be removed with 2.0.0 at the latest",
    ReplaceWith("ch.tutteli.atrium.creating.typeutils.impl.DefaultIterableLikeToIterableTransformer")
)
class DefaultIterableLikeToIterableTransformer : IterableLikeToIterableTransformer {

    override fun <T> unsafeTransform(iterableLike: IterableLike): Iterable<T> {
        val iAny: Iterable<*> = when (iterableLike) {
            is Iterable<*> -> iterableLike
            is Sequence<*> -> iterableLike.asIterable()
            is Array<*> -> iterableLike.toList()
            is CharArray -> iterableLike.toList()
            is ByteArray -> iterableLike.toList()
            is ShortArray -> iterableLike.toList()
            is IntArray -> iterableLike.toList()
            is LongArray -> iterableLike.toList()
            is FloatArray -> iterableLike.toList()
            is DoubleArray -> iterableLike.toList()
            is BooleanArray -> iterableLike.toList()
            else -> throw IllegalArgumentException(
                StringBuilder("IterableLikeToIterableTransformer accepts arguments of types: ").also { sb ->
                    supportedTypes().appendToStringBuilder(sb, ", ", " and ") { sb.append(it) }
                }.also { sb ->
                    sb.append("\ngiven: ${iterableLike::class.fullName(iterableLike)}")
                }.toString()
            )
        }

        @Suppress(
            // We push the responsibility to define a correct `T` to the caller and therefore it is OK
            // because we don't change the elements of IterableLike above
            "UNCHECKED_CAST"
        )
        return iAny as Iterable<T>
    }

    override fun supportedTypes(): List<String> = listOf(
        "Iterable<*>",
        "Sequence<*>",
        "Array<*>",
        "CharArray",
        "ByteArray",
        "ShortArray",
        "IntArray",
        "LongArray",
        "FloatArray",
        "DoubleArray",
        "BooleanArray"
    )
}



