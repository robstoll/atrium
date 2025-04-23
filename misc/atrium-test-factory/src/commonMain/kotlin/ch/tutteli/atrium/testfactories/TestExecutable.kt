package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationVerbs

/**
 * Represents a test as such and provides an abstraction over which expectation verb shall be used.
 *
 * If the platform supports hierarchical tests, then the used verb corresponds to a regular (root) expectation verb.
 * On the other hand, if the platform does not support it, then it corresponds to an expectation verb which is based on
 * [ExpectGrouping].
 *
 * @since 1.3.0
 */
interface TestExecutable

/**
 * Represents the contract each [TestExecutable] implementation has to follow.
 * Not implementing it as well will lead to a [ClassCastException] at runtime.
 *
 * It's on purpose separated from [TestExecutable] to not reveal implementation details to the end-user.
 *
 * @since 1.3.0
 */
interface TestExecutableImplContract : TestExecutable {
    val expectationVerbs: ExpectationVerbs
}

