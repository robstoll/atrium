//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.logic.creating.typeutils

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.logic.creating.typeutils.impl.DefaultIterableLikeToIterableTransformer
import ch.tutteli.atrium.logic.creating.typeutils.impl.DefaultMapLikeToIterablePairTransformer

@OptIn(ExperimentalNewExpectTypes::class)
@Deprecated("Switch to ProofContainer and use the import from atrium-core, AssertionContainer and atrium-logic will be removed with 2.0.0 at the latest")
val AssertionContainer<*>.iterableLikeToIterableTransformer: IterableLikeToIterableTransformer
    get() = getImpl(IterableLikeToIterableTransformer::class) { DefaultIterableLikeToIterableTransformer() }

@OptIn(ExperimentalNewExpectTypes::class)
@Deprecated("Switch to ProofContainer and use the import from atrium-core, AssertionContainer and atrium-logic will be removed with 2.0.0 at the latest")
val AssertionContainer<*>.mapLikeToMapTransformer: MapLikeToIterablePairTransformer
    get() = getImpl(MapLikeToIterablePairTransformer::class) { DefaultMapLikeToIterablePairTransformer(this) }
