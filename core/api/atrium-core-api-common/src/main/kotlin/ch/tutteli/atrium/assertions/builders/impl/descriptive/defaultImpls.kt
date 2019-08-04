package ch.tutteli.atrium.assertions.builders.impl.descriptive

import ch.tutteli.atrium.assertions.*
import ch.tutteli.atrium.assertions.builders.Descriptive
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.SubjectProvider
import ch.tutteli.atrium.reporting.RawString
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
        subjectProvider: SubjectProvider<T>,
        test: (T) -> Boolean
    ): Descriptive.DescriptionOption<Descriptive.FinalStep> = withTest {
        subjectProvider.maybeSubject.fold(trueProvider, test)
    }
}


internal class DescriptionOptionImpl<R>(
    override val test: () -> Boolean,
    private val factory: (() -> Boolean, Translatable, Any) -> R
) : Descriptive.DescriptionOption<R> {

    override fun withDescriptionAndRepresentation(description: Translatable, representation: Any?): R =
        factory(test, description, representation ?: RawString.NULL)
}


internal class FinalStepImpl(
    override val test: () -> Boolean,
    override val description: Translatable,
    override val representation: Any
) : Descriptive.FinalStep {

    @Suppress("DEPRECATION")
    override fun build(): DescriptiveAssertion = BasicDescriptiveAssertion(description, representation, test)
}
