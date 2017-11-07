package ch.tutteli.atrium.assertions

import ch.tutteli.atrium.assertions.DescriptionThrowableAssertion.IS_A
import ch.tutteli.atrium.assertions.DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED
import ch.tutteli.atrium.creating.IAssertionPlant
import ch.tutteli.atrium.creating.IThrowableFluent

inline fun <reified TExpected : Throwable> _toThrow(throwableFluent: IThrowableFluent, noinline createAssertions: IAssertionPlant<TExpected>.() -> Unit) {
   throwableFluent.toThrow(TExpected::class, IS_A, NO_EXCEPTION_OCCURRED, createAssertions)
}
