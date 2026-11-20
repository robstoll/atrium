package ch.tutteli.atrium.reporting.erroradjusters

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.core.polyfills.stackBacktrace
import ch.tutteli.atrium.creating.ComponentFactoryContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import ch.tutteli.atrium.reporting.AtriumErrorAdjuster
import kotlin.test.Test

class AdjustStackTest {

    @OptIn(ExperimentalWithOptions::class, ExperimentalComponentFactoryContainer::class)
    private fun <T> expectWithNoOpErrorAdjuster(subject: T) =
        expect(subject).withOptions {
            withComponent(AtriumErrorAdjuster::class) { _ -> NoOpAtriumErrorAdjuster }
        }

    private fun <T> expectWithNoOpErrorAdjuster(subject: T, assertionCreator: Expect<T>.() -> Unit): Expect<T> =
        expectWithNoOpErrorAdjuster(subject)._logic.appendAsGroup(assertionCreator)

    @Test
    fun noOp_containsMochaAndAtrium() {
        expectWithNoOpErrorAdjuster {
            assertNoOp(1).toEqual(2)
        }.toThrow<AssertionError> {
            feature(AssertionError::stackBacktrace).toContain(
                { toContainRegex(Regex("""[\\|/]node_modules[\\|/]mocha[\\|/]""")) },
                { toContain("createAtriumError") }
            )
        }
    }

    @Test
    fun noOp_makeSureStackBacktraceIsInOutput_issue1383() {
        expectWithNoOpErrorAdjuster {
            expectWithNoOpErrorAdjuster {
                assertNoOp(1).toEqual(2)
            }.toThrow<AssertionError> {
                feature(AssertionError::stackBacktrace).toContain(
                    { toContain("createAtriumError2") }
                )
            }
        }.toThrow<AssertionError> {
            messageToContain("stackBacktrace")
        }
    }

    @Test
    fun removeRunner_containsAtriumButNotMocha() {
        expectWithNoOpErrorAdjuster {
            assertRemoveRunner(1).toEqual(2)
        }.toThrow<AssertionError> {
            feature(AssertionError::stackBacktrace) {
                notToContain.entry { toContain("mocha") }
                notToContain.entry { toContain("KotlinTestTeamCityConsoleAdapter") }
                toContain { toContain("createAtriumError") }
            }
        }
    }

    @Test
    fun removeRunner_containsAtriumButNotMochaInCause() {
        assertRemoveRunner {
            throw IllegalArgumentException("hello", UnsupportedOperationException("world"))
        }.toThrow<IllegalArgumentException> {
            cause<UnsupportedOperationException> {
                feature(UnsupportedOperationException::stackBacktrace) {
                    notToContain.entry { toContain("mocha") }
                    notToContain.entry { toContain("KotlinTestTeamCityConsoleAdapter") }
                    toContain { toContainRegex(Regex("""atrium[\\|/].*[\\|/]src[\\|/].*[\\|/]ch[\\|/]tutteli[\\|/]atrium""")) }
                }
            }
        }
    }

    @Test
    fun removeAtrium_containsMochaButNotAtrium() {
        expectWithNoOpErrorAdjuster {
            assertRemoveAtrium(1).toEqual(2)
        }.toThrow<AssertionError> {
            feature(AssertionError::stackBacktrace) {
                toContain { toContain("mocha") }
                notToContain.entry {
                    toContainRegex("""atrium[\\|/].*[\\|/]src[\\|/].*[\\|/]ch[\\|/]tutteli[\\|/]atrium""")
                    // this will show up in the stacktrace and also have the atrium path, hence we exclude it
                    notToStartWith("AdjustStackTest")
                }
            }
        }
    }

    @ExperimentalComponentFactoryContainer
    @Test
    fun removeAtrium_containsMochaButNotAtriumInCause() {
        val adjuster = assertRemoveAtrium(1)._logic.components.build<AtriumErrorAdjuster>()
        expectWithNoOpErrorAdjuster(adjuster).toBeAnInstanceOf<RemoveAtriumFromAtriumError>()
        val throwable = IllegalArgumentException("hello", UnsupportedOperationException("world"))
        adjuster.adjust(throwable)
        expectWithNoOpErrorAdjuster(throwable.cause!!.stackBacktrace) {
            toContain { toContain("mocha") }
            notToContain.entry { toContain("atrium-core-js") }
        }
    }

    @OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
    private fun <T : Any> assertNoOp(subject: T) = createExpect(subject) { NoOpAtriumErrorAdjuster }

    @OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
    private fun <T : Any> assertRemoveRunner(subject: T) =
        createExpect(subject) { c -> c.build<RemoveRunnerFromAtriumError>() }

    @OptIn(ExperimentalNewExpectTypes::class, ExperimentalComponentFactoryContainer::class)
    private fun <T : Any> assertRemoveAtrium(subject: T) =
        createExpect(subject) { c -> c.build<RemoveAtriumFromAtriumError>() }


    @ExperimentalNewExpectTypes
    @ExperimentalComponentFactoryContainer
    private fun <T : Any> createExpect(subject: T, factory: (ComponentFactoryContainer) -> AtriumErrorAdjuster) =
        RootExpectBuilder.forSubject(subject)
            .withVerb("I expected subject")
            .withOptions {
                withComponent(AtriumErrorAdjuster::class, factory)
            }
            .build()
}
