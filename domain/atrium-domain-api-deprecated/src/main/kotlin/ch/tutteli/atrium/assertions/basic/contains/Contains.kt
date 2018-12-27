@file:Suppress("DEPRECATION" /* TODO remove with 1.0.0 */)
package ch.tutteli.atrium.assertions.basic.contains

import ch.tutteli.atrium.assertions.basic.contains.Contains.*

/**
 * Defines the *deprecated* basic contract for sophisticated `contains` assertion builders.
 *
 * A builder typically allows a user to choose a desired [SearchBehaviour], one or more [Checker]s and uses an
 * [Creator] to finish the building process.
 */
@Deprecated(
    "use the interface from package domain.creating; will be removed with 1.0.0",
    ReplaceWith("ch.tutteli.atrium.domain.creating.basic.contains.Contains")
)
interface Contains {

    @Deprecated(
        "use the interface from package domain.creating; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.domain.creating.basic.contains.Contains.SearchBehaviour")
    )
    interface SearchBehaviour : ch.tutteli.atrium.domain.creating.basic.contains.Contains.SearchBehaviour

    @Deprecated(
        "use the interface from package domain.creating; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.domain.creating.basic.contains.Contains.Checker")
    )
    interface Checker : ch.tutteli.atrium.domain.creating.basic.contains.Contains.Checker

    @Deprecated(
        "use the interface from package domain.creating; will be removed with 1.0.0",
        ReplaceWith("ch.tutteli.atrium.domain.creating.basic.contains.Contains.Creator")
    )
    interface Creator<in T : Any, in SC> : ch.tutteli.atrium.domain.creating.basic.contains.Contains.Creator<T, SC>
}
