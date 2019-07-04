package ch.tutteli.atrium.assertions.builders.impl.descriptive

import ch.tutteli.atrium.assertions.builders.Descriptive
import ch.tutteli.atrium.core.trueProvider
import ch.tutteli.atrium.creating.SubjectProvider

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
