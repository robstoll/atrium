package ch.tutteli.atrium.creating.typeutils

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.ProofContainer
import ch.tutteli.atrium.creating.typeutils.impl.DefaultIterableLikeToIterableTransformer
import ch.tutteli.atrium.creating.typeutils.impl.DefaultMapLikeToIterablePairTransformer

@OptIn(ExperimentalNewExpectTypes::class)
val ProofContainer<*>.iterableLikeToIterableTransformer: IterableLikeToIterableTransformer
    get() = getImpl(IterableLikeToIterableTransformer::class) { DefaultIterableLikeToIterableTransformer() }

@OptIn(ExperimentalNewExpectTypes::class)
val ProofContainer<*>.mapLikeToMapTransformer: MapLikeToIterablePairTransformer
    get() = getImpl(MapLikeToIterablePairTransformer::class) { DefaultMapLikeToIterablePairTransformer(this) }
