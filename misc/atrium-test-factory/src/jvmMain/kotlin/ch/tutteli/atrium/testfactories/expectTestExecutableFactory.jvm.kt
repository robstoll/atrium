package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.testfactories.impl.RootExpectTestExecutable

actual fun expectTestExecutableFactory(expectationVerbs: ExpectationVerbs): () -> ExpectTestExecutable = {
    RootExpectTestExecutable(expectationVerbs)
}
