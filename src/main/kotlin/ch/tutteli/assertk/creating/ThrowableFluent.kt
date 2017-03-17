package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.ExceptionThrownAssertion
import ch.tutteli.assertk.checking.IAssertionChecker

class ThrowableFluent(val assertionVerb: String, val throwable: Throwable?, val assertionChecker: IAssertionChecker) {

    inline fun <reified TExpected : Throwable> toThrow(): IAssertionPlant<TExpected>
        = DownCastFluent.create<TExpected, Throwable>(toAssertionFactoryCommonFields(), ExceptionThrownAssertion(throwable, TExpected::class.java))
        .withNullRepresentation(NO_EXCEPTION_OCCURRED)
        .cast()

    inline fun <reified TExpected : Throwable> toThrow(noinline createAsserts: IAssertionPlant<TExpected>.() -> Unit): IAssertionPlant<TExpected>
        = DownCastFluent.create<TExpected, Throwable>(toAssertionFactoryCommonFields(), ExceptionThrownAssertion(throwable, TExpected::class.java))
        .withNullRepresentation(NO_EXCEPTION_OCCURRED)
        .withLazyAssertions(createAsserts)
        .cast()

    fun toAssertionFactoryCommonFields() = IAssertionPlantWithCommonFields.CommonFields(assertionVerb, throwable, assertionChecker)

    companion object {
        val NO_EXCEPTION_OCCURRED = "no exception occurred"

        fun create(assertionVerb: String, act: () -> Unit, assertionChecker: IAssertionChecker): ThrowableFluent {
            var throwable: Throwable? = null
            try {
                act()
            } catch(t: Throwable) {
                throwable = t
            }
            return ThrowableFluent(assertionVerb, throwable, assertionChecker)
        }
    }
}
