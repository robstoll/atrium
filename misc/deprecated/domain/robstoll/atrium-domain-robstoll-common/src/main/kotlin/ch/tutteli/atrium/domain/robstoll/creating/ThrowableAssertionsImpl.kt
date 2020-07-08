//TODO remove file with 1.0.0
@file:Suppress("DEPRECATION")
package ch.tutteli.atrium.domain.robstoll.creating

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.domain.creating.ThrowableAssertions
import ch.tutteli.atrium.domain.creating.changers.ChangedSubjectPostStep
import ch.tutteli.atrium.domain.robstoll.lib.creating._cause
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable
import kotlin.reflect.KClass

@Deprecated("Will be removed with 1.0.0")
class ThrowableAssertionsImpl : ThrowableAssertions {

    override fun <TExpected : Throwable> cause(
        expect: Expect<out Throwable>,
        expectedType: KClass<TExpected>
    ): ChangedSubjectPostStep<Throwable?, TExpected> =
        _cause(expect, expectedType)


    @Suppress("OverridingDeprecatedMember", "DEPRECATION")
    override fun thrownBuilder(
        assertionVerb: Translatable,
        act: () -> Unit,
        reporter: Reporter
    ): ch.tutteli.atrium.domain.creating.throwable.thrown.ThrowableThrown.Builder =
        ch.tutteli.atrium.domain.robstoll.lib.creating.throwable.thrown.builders.ThrowableThrownBuilder(
            assertionVerb, act, reporter
        )
}

