package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.api.verbs.internal.testFactory
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.SpecPair
import ch.tutteli.atrium.specs.lambda
import ch.tutteli.atrium.specs.subjectLessTestSetup
import ch.tutteli.atrium.testfactories.ExpectTestExecutable
import ch.tutteli.atrium.testfactories.TestFactoryBuilder

actual abstract class AbstractBaseTest {
    protected actual fun <T> subjectLessTestFactory(
        assertionCreator: Pair<String, Expect<T>.() -> Unit>,
        vararg otherAssertionCreators: Pair<String, Expect<T>.() -> Unit>,
        groupPrefix: String?
    ): Any = this.let { self ->
        testFactory {
            describe(
                "${self::class.simpleName} - subjectLessTest${groupPrefix?.let { "- $it" } ?: ""}",
                subjectLessTestSetup(assertionCreator, otherAssertionCreators)
            )
        }
    }

    protected actual fun <T> testFactory(
        specPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutable>.(T) -> Unit
    ): Any =
        this.let { self ->
            testFactory {
                describe("${self::class.simpleName} - ${specPair.first}") { setup(specPair.lambda) }
            }
        }
}
