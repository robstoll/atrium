package ch.tutteli.atrium.api.verbs.internal.testfactories

import ch.tutteli.atrium.api.verbs.internal.testfactories.impl.Block
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.testfactories.expect.ExpectTestExecutable

interface ExpectTestExecutableForTests : ExpectTestExecutable {
    fun expect(b: Block.() -> Unit): Expect<() -> Unit> =
        expect<() -> Unit> { Block(expectationVerbs).apply(b) }
}
