package ch.tutteli.atrium.domain.robstoll.creating.throwable.thrown.creators

import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown
import ch.tutteli.atrium.domain.creating.throwable.thrown.creators.ThrowableThrownAssertions
import ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators._isThrown
import ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.creators._notThrown
import kotlin.reflect.KClass


class ThrowableThrownAssertionsImpl : ThrowableThrownAssertions, ThrowableThrownAssertionsDeprecatedImpl() {

    override fun <TExpected : Throwable> isA(
        throwableThrownBuilder: ThrowableThrown.Builder,
        expectedType: KClass<TExpected>
    ) = _isThrown(throwableThrownBuilder, expectedType)

    override fun notThrown(
        throwableThrownBuilder: ThrowableThrown.Builder
    ): ChangedSubjectPostStep<Throwable?, Nothing?> = _notThrown(throwableThrownBuilder)
}
