//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.typeutils

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.typeutils.impl.DefaultIterableLikeToIterableTransformer
import ch.tutteli.atrium.logic.creating.typeutils.impl.DefaultMapLikeToIterablePairTransformer

@OptIn(ExperimentalNewExpectTypes::class)
val AssertionContainer<*>.iterableLikeToIterableTransformer: IterableLikeToIterableTransformer
    get() = getImpl(IterableLikeToIterableTransformer::class) { DefaultIterableLikeToIterableTransformer() }

@OptIn(ExperimentalNewExpectTypes::class)
val AssertionContainer<*>.mapLikeToMapTransformer: MapLikeToIterablePairTransformer
    get() = getImpl(MapLikeToIterablePairTransformer::class) { DefaultMapLikeToIterablePairTransformer(this) }
