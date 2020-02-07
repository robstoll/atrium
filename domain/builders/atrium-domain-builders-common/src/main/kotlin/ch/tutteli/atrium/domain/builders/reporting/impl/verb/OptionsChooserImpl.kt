package ch.tutteli.atrium.domain.builders.reporting.impl.verb

import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder
import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

class OptionsChooserImpl<T> : ExpectBuilder.OptionsChooser<T> {

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

    fun build(): ExpectOptions<T> = ExpectOptions(description, representationInsteadOfSubject, reporter)
}
