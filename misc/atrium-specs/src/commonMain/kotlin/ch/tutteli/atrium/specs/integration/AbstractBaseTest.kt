package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.specs.SpecPair
import ch.tutteli.atrium.testfactories.ExpectTestExecutable
import ch.tutteli.atrium.testfactories.TestFactoryBuilder

expect abstract class AbstractBaseTest() {
    protected fun <T> testFactory(
        specPair: SpecPair<T>,
        setup: TestFactoryBuilder<ExpectTestExecutable>.(T) -> Unit
    ): Any

    protected fun <T> subjectLessTestFactory(
        assertionCreator: Pair<String, Expect<T>.() -> Unit>,
        vararg otherAssertionCreators: Pair<String, Expect<T>.() -> Unit>,
        groupPrefix: String? = null
    ): Any
}
