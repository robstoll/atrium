package ch.tutteli.atrium.logic.creating.impl

import ch.tutteli.atrium.core.ExperimentalNewExpectTypes
import ch.tutteli.atrium.creating.RootExpectOptions
import ch.tutteli.atrium.logic.creating.RootExpectBuilder
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

@ExperimentalNewExpectTypes
class OptionsChooserImpl<T> : RootExpectBuilder.OptionsChooser<T> {
    private var description: Translatable? = null
    private var representationInsteadOfSubject: ((T) -> Any)? = null
    private var reporter: Reporter? = null

    override fun withVerb(verb: Translatable) {
        this.description = verb
    }

    override fun withRepresentation(representationProvider: (T) -> Any) {
        this.representationInsteadOfSubject = representationProvider
    }

    override fun withReporter(reporter: Reporter) {
        this.reporter = reporter
    }

    fun build(): RootExpectOptions<T> = RootExpectOptions(description, representationInsteadOfSubject, reporter)
}
