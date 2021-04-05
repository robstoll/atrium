package ch.tutteli.atrium.assertions.builders.common.impl

import ch.tutteli.atrium.assertions.builders.common.HoldsStep
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect

internal abstract class HoldsStepImpl<R> : HoldsStep<R> {
    //TODO use falseProvider https://youtrack.jetbrains.com/issue/KT-27736
    override val failing: R = withTest { false }
    //TODO use trueProvider https://youtrack.jetbrains.com/issue/KT-27736
    override val holding: R = withTest { true }

    override fun <T> withTest(expect: Expect<T>, test: (T) -> Boolean): R = withTest {
        @Suppress("UNCHECKED_CAST")
        (expect as AssertionContainer<T>).maybeSubject.fold(falseProvider, test)
    }

    final override fun withTest(test: () -> Boolean): R = createNextStep(test)

    protected abstract fun createNextStep(test: () -> Boolean): R
}
