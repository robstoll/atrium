package readme.examples.utils

import ch.tutteli.atrium._core
import ch.tutteli.atrium.api.fluent.en_GB.ExperimentalWithOptions
import ch.tutteli.atrium.api.fluent.en_GB.withOptions
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExpectationCreatorWithUsageHints
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.reporting.text.TextObjectFormatter
import ch.tutteli.atrium.reporting.text.impl.AbstractTextObjectFormatter
import ch.tutteli.atrium.reporting.theming.text.TextIconStyler
import ch.tutteli.atrium.reporting.theming.text.impl.MarkdownTextIconStyler
import ch.tutteli.atrium.api.verbs.expect as atriumsExpect
import ch.tutteli.atrium.api.verbs.expectGrouped as atriumsExpectGrouped

@OptIn(ExperimentalWithOptions::class, ExperimentalComponentFactoryContainer::class)
fun <T> expect(t: T): Expect<T> =
    atriumsExpect(t).withOptions {
        withSingletonComponent(TextObjectFormatter::class) { _ -> ReadmeObjectFormatter() }
        withComponent(TextIconStyler::class) { _ -> MarkdownTextIconStyler() }
    }

@OptIn(ExperimentalComponentFactoryContainer::class)
fun expectGrouped(
    groupingActions: ExpectGrouping.() -> Unit,
): ExpectGrouping =
    atriumsExpectGrouped(
        configuration = {
            withSingletonComponent(TextObjectFormatter::class) { _ -> ReadmeObjectFormatter() }
            withComponent(TextIconStyler::class) { _ -> MarkdownTextIconStyler() }
        },
        groupingActions = groupingActions
    )

fun <T> expect(t: T, assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    expect(t)._core.appendAsGroupIndicateIfOneCollected(
        ExpectationCreatorWithUsageHints(
            // we don't have an alternative, we always expect expectations and hence we don't provide a failure hint
            // (proposing `expect(subject).` as alternative would be wrong as we also expect further expectation)
            usageHintsAlternativeWithoutExpectationCreator = emptyList(),
            expectationCreator = assertionCreator
        )
    ).first

fun <R> ExpectGrouping.expect(subject: R): Expect<R> = atriumsExpect(subject)

fun <R> ExpectGrouping.expect(subject: R, assertionCreator: Expect<R>.() -> Unit): Expect<R> =
    atriumsExpect(subject, assertionCreator)


class ReadmeObjectFormatter() : AbstractTextObjectFormatter() {

    override fun identityHash(indent: String, any: Any): String =
        "$indent<1234789>" // dummy hash so it does not change all the time
}
