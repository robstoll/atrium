package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.assertions.Assertion
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.MetaFeature
import ch.tutteli.atrium.domain.creating.NewFeatureAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating._genericFeature

class NewFeatureAssertionsImpl : NewFeatureAssertions {

    override fun <T, R> genericFeature(assertionContainer: Expect<T>, metaFeature: MetaFeature<R>): Expect<R> =
        _genericFeature(assertionContainer, metaFeature)

    override fun <T, R> genericFeature(
        assertionContainer: Expect<T>,
        metaFeature: MetaFeature<R>,
        assertionCreator: Expect<R>.() -> Unit
    ): Assertion = _genericFeature(assertionContainer, metaFeature, assertionCreator)
}
