package ch.tutteli.atrium.assertions.builders.common.impl

import ch.tutteli.atrium.assertions.builders.common.HoldsStep
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.toProofContainer

internal abstract class HoldsStepImpl<R> : HoldsStep<R> {
    //TODO 1.4.0 use falseProvider https://youtrack.jetbrains.com/issue/KT-27736
    override val failing: R = withTest { false }

    //TODO 1.4.0 use trueProvider https://youtrack.jetbrains.com/issue/KT-27736
    override val holding: R = withTest { true }

    override fun <T> withTest(expect: Expect<T>, test: (T) -> Boolean): R =
        withTest {
            expect.toProofContainer().maybeSubject.fold(falseProvider, test)
        }

    final override fun withTest(test: () -> Boolean): R = createNextStep(test)

    protected abstract fun createNextStep(test: () -> Boolean): R
}
