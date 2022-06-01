package ch.tutteli.atrium.logic.creating.typeutils

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.typeutils.impl.DefaultIterableLikeToIterableTransformer
import ch.tutteli.atrium.logic.creating.typeutils.impl.DefaultMapLikeToIterablePairTransformer

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
val AssertionContainer<*>.iterableLikeToIterableTransformer: IterableLikeToIterableTransformer
    get() = getImpl(IterableLikeToIterableTransformer::class) { DefaultIterableLikeToIterableTransformer() }

@Suppress("DEPRECATION" /* OptIn is only available since 1.3.70 which we cannot use if we want to support 1.2 */)
@UseExperimental(ExperimentalNewExpectTypes::class)
val AssertionContainer<*>.mapLikeToMapTransformer: MapLikeToIterablePairTransformer
    get() = getImpl(MapLikeToIterablePairTransformer::class) { DefaultMapLikeToIterablePairTransformer(this) }
