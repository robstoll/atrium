//TODO remove file with 2.0.0 at the latest
@file:Suppress("DEPRECATION")

package ch.tutteli.atrium.assertions.builders.impl.descriptive

import ch.tutteli.atrium.assertions.DescriptiveAssertion
import ch.tutteli.atrium.assertions.builders.Descriptive
import ch.tutteli.atrium.core.falseProvider
import ch.tutteli.atrium.creating.AssertionContainer
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.translating.Translatable


internal object HoldsOptionImpl : Descriptive.HoldsOption {

    override val failing: Descriptive.DescriptionOption<Descriptive.FinalStep>
    //TODO use falseProvider https://youtrack.jetbrains.com/issue/KT-27736
        = withTest { false }

    override val holding: Descriptive.DescriptionOption<Descriptive.FinalStep>
    //TODO use trueProvider https://youtrack.jetbrains.com/issue/KT-27736
        = withTest { true }

    override fun withTest(test: () -> Boolean): Descriptive.DescriptionOption<Descriptive.FinalStep> =
        Descriptive.DescriptionOption.create(test, Descriptive.FinalStep.Companion::create)

    override fun <T> withTest(
        expect: Expect<T>,
        test: (T) -> Boolean
    ): Descriptive.DescriptionOption<Descriptive.FinalStep> = withTest {
        @Suppress("UNCHECKED_CAST")
        (expect as AssertionContainer<T>).maybeSubject.fold(falseProvider, test)
    }
}


internal class DescriptionOptionImpl<R>(
    override val test: () -> Boolean,
    private val factory: (() -> Boolean, Translatable, Any) -> R
) : Descriptive.DescriptionOption<R> {

    override fun withDescriptionAndRepresentation(description: Translatable, representation: Any?): R =
        factory(test, description, representation ?: Text.NULL)
}


internal class FinalStepImpl(
    override val test: () -> Boolean,
    override val description: Translatable,
    override val representation: Any
) : Descriptive.FinalStep {

    override fun build(): DescriptiveAssertion =
        ch.tutteli.atrium.assertions.BasicDescriptiveAssertion(description, representation, test)
}
