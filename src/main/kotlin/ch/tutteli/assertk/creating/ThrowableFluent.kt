package ch.tutteli.assertk.creating

import ch.tutteli.assertk.assertions.ExceptionThrownAssertion
import ch.tutteli.assertk.checking.IAssertionChecker

class ThrowableFluent(val commonFields: IAssertionPlantWithCommonFields.CommonFields<Throwable?>) {

    constructor(assertionVerb: String, throwable: Throwable?, assertionChecker: IAssertionChecker)
        : this(IAssertionPlantWithCommonFields.CommonFields(assertionVerb, throwable, assertionChecker))

    inline fun <reified TExpected : Throwable> toThrow(): IAssertionPlant<TExpected>
        = DownCastBuilder.create<TExpected, Throwable>(commonFields, ExceptionThrownAssertion(commonFields.subject, TExpected::class.java))
        .withNullRepresentation(NO_EXCEPTION_OCCURRED)
        .cast()

    inline fun <reified TExpected : Throwable> toThrow(noinline createAsserts: IAssertionPlant<TExpected>.() -> Unit): IAssertionPlant<TExpected>
        = DownCastBuilder.create<TExpected, Throwable>(commonFields, ExceptionThrownAssertion(commonFields.subject, TExpected::class.java))
        .withNullRepresentation(NO_EXCEPTION_OCCURRED)
        .withLazyAssertions(createAsserts)
        .cast()

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
