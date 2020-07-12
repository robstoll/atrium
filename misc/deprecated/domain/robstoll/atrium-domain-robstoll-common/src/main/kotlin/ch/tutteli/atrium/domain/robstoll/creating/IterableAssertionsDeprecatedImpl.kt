@file:Suppress("DEPRECATION" /* TODO remove annotation with 1.0.0 */)

package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.IterableAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._iterableAll


abstract class IterableAssertionsDeprecatedImpl : IterableAssertions {

    override fun <E : Any> all(
        plant: AssertionPlant<Iterable<E?>>,
        assertionCreator: (AssertionPlant<E>.() -> Unit)?
    ): Assertion = _iterableAll(plant, assertionCreator)
}
