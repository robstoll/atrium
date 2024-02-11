package readme.examples.utils

import ch.tutteli.atrium.api.fluent.en_GB.ExperimentalWithOptions
import ch.tutteli.atrium.api.fluent.en_GB.withOptions
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExpectGrouping
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.logic._logic
import ch.tutteli.atrium.reporting.text.TextObjectFormatter
import ch.tutteli.atrium.reporting.text.impl.AbstractTextObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.api.verbs.expect as atriumsExpect
import ch.tutteli.atrium.api.verbs.expectGrouped as atriumsExpectGrouped

@OptIn(ExperimentalWithOptions::class, ExperimentalComponentFactoryContainer::class)
fun <T> expect(t: T): Expect<T> =
    atriumsExpect(t).withOptions {
        withSingletonComponent(TextObjectFormatter::class) { c -> ReadmeObjectFormatter(c.build()) }
    }

@OptIn(ExperimentalWithOptions::class, ExperimentalComponentFactoryContainer::class)
fun expectGrouped(
    groupingActions: ExpectGrouping.() -> Unit,
): ExpectGrouping =
    atriumsExpectGrouped(
        configuration = {
            withSingletonComponent(TextObjectFormatter::class) { c -> ReadmeObjectFormatter(c.build()) }
        },
        groupingActions = groupingActions
    )

fun <T> expect(t: T, assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    expect(t)._logic.appendAsGroup(assertionCreator)

fun <R> ExpectGrouping.expect(subject: R): Expect<R> = atriumsExpect(subject)

fun <R> ExpectGrouping.expect(subject: R, assertionCreator: Expect<R>.() -> Unit): Expect<R> =
    atriumsExpect(subject, assertionCreator)


class ReadmeObjectFormatter(translator: Translator) : AbstractTextObjectFormatter(translator) {

    override fun identityHash(indent: String, any: Any): String =
        "$indent<1234789>" // dummy hash so it does not change all the time
}
