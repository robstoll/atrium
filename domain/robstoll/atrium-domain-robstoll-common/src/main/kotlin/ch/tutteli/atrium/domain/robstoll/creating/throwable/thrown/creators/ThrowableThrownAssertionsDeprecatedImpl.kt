package ch.tutteli.atrium.domain.robstoll.creating.throwable.thrown.creators

import ch.tutteli.atrium.creating.AssertionPlant
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.domain.creating.throwable.thrown.creators.ThrowableThrownAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators._nothingThrown
import ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators._toBe
import kotlin.reflect.KClass


abstract class ThrowableThrownAssertionsDeprecatedImpl : ThrowableThrownAssertions {

    override fun <TExpected : Throwable> toBe(
        throwableThrownBuilder: ThrowableThrown.Builder,
        expectedType: KClass<TExpected>,
        assertionCreator: AssertionPlant<TExpected>.() -> Unit
    ) {
        _toBe(throwableThrownBuilder, expectedType, assertionCreator)
    }

    override fun nothingThrown(throwableThrownBuilder: ThrowableThrown.Builder) {
        _nothingThrown(throwableThrownBuilder)
    }
}
