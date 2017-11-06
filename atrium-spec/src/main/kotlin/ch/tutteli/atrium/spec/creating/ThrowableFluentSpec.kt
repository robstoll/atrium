package ch.tutteli.atrium.spec.creating

import ch.tutteli.atrium.api.cc.en_UK.*
import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.IS_SAME
import ch.tutteli.atrium.assertions.DescriptionThrowableAssertion.IS_A
import ch.tutteli.atrium.assertions.DescriptionThrowableAssertion.NO_EXCEPTION_OCCURRED
import ch.tutteli.atrium.checking.IAssertionChecker
import ch.tutteli.atrium.creating.IThrowableFluent
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.checkGenericNarrowingAssertion
import ch.tutteli.atrium.spec.prefixedDescribe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody

abstract class ThrowableFluentSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (assertionVerb: ITranslatable, act: () -> Unit, IAssertionChecker) -> IThrowableFluent,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    fun SpecBody.checkToThrow(description: String,
                              act: (IThrowableFluent.() -> Unit) -> Unit,
                              immediate: (IThrowableFluent.() -> Unit),
                              lazy: (IThrowableFluent.() -> Unit)) {
        checkGenericNarrowingAssertion(description, act, immediate, lazy)
    }


    //TODO move those tests to ThrowableAssertionSpec
    prefixedDescribe("fun toThrow") {

        checkToThrow("it throws an AssertionError when no exception occurs", { doToThrow ->
            verbs.checkException {
                verbs.checkException {
                    /* no exception occurs */
                }.doToThrow()
            }.toThrow<AssertionError>().and.message {
                containsDefaultTranslationOf(NO_EXCEPTION_OCCURRED)
                contains("${IS_A.getDefault()}: ${IllegalArgumentException::class.simpleName}")
            }
        }, { toThrow(IllegalArgumentException::class, IS_A, NO_EXCEPTION_OCCURRED) },
            { toThrow(IllegalArgumentException::class, IS_A, NO_EXCEPTION_OCCURRED) {} })

        checkToThrow("it throws an AssertionError when the wrong exception occurs", { doToThrow ->
            verbs.checkException {
                verbs.checkException {
                    throw UnsupportedOperationException()
                }.doToThrow()
            }.toThrow<AssertionError> {
                message.contains(UnsupportedOperationException::class.java.name, IS_SAME.getDefault(), IllegalArgumentException::class.java.name)
            }
        }, { toThrow(IllegalArgumentException::class, IS_SAME, NO_EXCEPTION_OCCURRED) },
            { toThrow(IllegalArgumentException::class, IS_SAME, NO_EXCEPTION_OCCURRED) {} })

        checkToThrow("it allows to define assertions for the Throwable if the correct exception is thrown", { toThrowWithCheck ->
            verbs.checkException {
                throw IllegalArgumentException("hello")
            }.toThrowWithCheck()
        }, { toThrow(IllegalArgumentException::class, IS_A, NO_EXCEPTION_OCCURRED).and.message.toBe("hello") },
            { toThrow(IllegalArgumentException::class, IS_A, NO_EXCEPTION_OCCURRED) { and.message.toBe("hello") } })
    }
})
