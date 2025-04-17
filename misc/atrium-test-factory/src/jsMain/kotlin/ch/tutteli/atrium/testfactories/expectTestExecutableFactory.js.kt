package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.creating.ExpectationVerbs
import ch.tutteli.atrium.testfactories.impl.ExpectGroupedBasedExpectTestExecutable

actual fun expectTestExecutableFactory(expectationVerbs: ExpectationVerbs): () -> ExpectTestExecutable = {
    ExpectGroupedBasedExpectTestExecutable(expectationVerbs)
}
