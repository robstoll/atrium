package ch.tutteli.atrium.logic.creating.typeutils

import ch.tutteli.atrium.logic.creating.typeutils.impl.DefaultIterableLikeToIterableTransformer

/**
 * Type alias for [Any] but with a better description what is expected at runtime,
 * i.e. a type which can be converted to an [Iterable] by the configured [IterableLikeToIterableTransformer].
 *
 * See [DefaultIterableLikeToIterableTransformer] for what is transformed if no custom
 * [IterableLikeToIterableTransformer] is specified.
 */
typealias IterableLike = Any
