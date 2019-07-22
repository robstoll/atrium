package ch.tutteli.atrium.domain.robstoll.lib.creating

import ch.tutteli.atrium.assertions.AssertionGroup
import ch.tutteli.atrium.core.getOrElse
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.builders.ExpectImpl
import ch.tutteli.atrium.domain.creating.MetaFeature
import ch.tutteli.atrium.domain.creating.changers.featureExtractor
import ch.tutteli.atrium.reporting.RawString


fun <T, R> _genericFeature(assertionContainer: Expect<T>, metaFeature: MetaFeature<R>): Expect<R> =
    genericFeature(assertionContainer, metaFeature, assertionCreator = null)

fun <T, R> _genericFeature(
    assertionContainer: Expect<T>,
    metaFeature: MetaFeature<R>,
    assertionCreator: Expect<R>.() -> Unit
): AssertionGroup = ExpectImpl.collector.collect(assertionContainer) {
    genericFeature(this, metaFeature, assertionCreator)
}

private fun <R, T> genericFeature(
    assertionContainer: Expect<T>,
    metaFeature: MetaFeature<R>,
    assertionCreator: (Expect<R>.() -> Unit)?
): Expect<R> {
    val representation: Any = metaFeature.representation ?: RawString.NULL
    return featureExtractor.extract(
        assertionContainer,
        metaFeature.description,
        representation,
        { metaFeature.maybeSubject.isDefined() },
        {
            metaFeature.maybeSubject.getOrElse {
                throw IllegalStateException("we checked that the subject is defined and suddenly it is not anymore.")
            }
        },
        assertionCreator,
        representation
    )
}
