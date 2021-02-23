package readme.examples.utils

import ch.tutteli.atrium.api.fluent.en_GB.ExperimentalWithOptions
import ch.tutteli.atrium.api.fluent.en_GB.withOptions
import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.creating.ExperimentalComponentFactoryContainer
import ch.tutteli.atrium.creating.build
import ch.tutteli.atrium.reporting.ObjectFormatter
import ch.tutteli.atrium.reporting.text.impl.AbstractTextObjectFormatter
import ch.tutteli.atrium.reporting.translating.Translator

@OptIn(ExperimentalWithOptions::class, ExperimentalComponentFactoryContainer::class)
fun <T> expect(t: T): Expect<T> =
    ch.tutteli.atrium.api.verbs.expect(t).withOptions {
        withComponent(ObjectFormatter::class) { c -> ReadmeObjectFormatter(c.build()) }
    }

fun <T> expect(t: T, assertionCreator: Expect<T>.() -> Unit): Expect<T> =
    expect(t).addAssertionsCreatedBy(assertionCreator)

class ReadmeObjectFormatter(translator: Translator) : AbstractTextObjectFormatter(translator) {

    override fun identityHash(indent: String, any: Any): String =
        "$indent<1234789>" // dummy hash so it does not change all the time
}
