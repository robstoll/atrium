package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.SpecPair
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.testfactories.ExpectTestExecutable
import ch.tutteli.atrium.testfactories.TestFactoryBuilder

actual abstract class AbstractBaseTest {

    protected actual fun <T> subjectLessTestFactory(
        assertionCreator: Pair<String, Expect<T>.() -> Unit>,
        vararg otherAssertionCreators: Pair<String, Expect<T>.() -> Unit>,
        groupPrefix: String?
    ): Any = ch.tutteli.atrium.api.verbs.internal.testFactory {
        describe("${groupPrefix?.let { "$it -" }}check expectation function can be used in a proof explanation without subject defined and does not throw") {
            this.apply(ch.tutteli.atrium.specs.subjectLessTestSetup(assertionCreator, otherAssertionCreators))
        }
    }

    protected actual fun <T> testFactory(
        specPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutable>.(T) -> Unit
    ): Any =
        ch.tutteli.atrium.api.verbs.internal.testFactory {
            describe(specPair.first) { setup(specPair.lambda) }
        }
}
