package ch.tutteli.atrium.api.infix.en_GB.creating.feature

import ch.tutteli.atrium.creating.Expect

/**
 * Parameter object which contains a [failureDescription] together with an [assertionCreator]-lambda where the subject
 * is passed in contrast to usual assertionCreator-lambdas.
 *
 * Use `withFailureDescription("failure description") { ... }` to create this representation where the first argument
 * is the failure description.
 *
 * @property failureDescription Provides the failure description (in case the subject cannot be extracted).
 * @property assertionCreator The assertionCreator-lambda responsible to create assertions.
 *
 * @since 1.2.0
 */
data class FailureDescriptionWithCreator<T> internal constructor(
    val failureDescription: String,
    val assertionCreator: Expect<T>.(T) -> Unit
)
