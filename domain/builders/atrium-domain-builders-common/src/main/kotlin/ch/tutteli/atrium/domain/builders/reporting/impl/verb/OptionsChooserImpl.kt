package ch.tutteli.atrium.domain.builders.reporting.impl.verb

import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder
import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions
import ch.tutteli.atrium.reporting.Reporter
import ch.tutteli.atrium.reporting.translating.Translatable

class OptionsChooserImpl : ExpectBuilder.OptionsChooser {

    private var description: Translatable? = null
    private var representation: Any? = null
    private var nullRepresentation: Any? = null
    private var reporter: Reporter? = null

    override fun withVerb(verb: Translatable) {
        this.description = verb
    }

    override fun withRepresentation(representation: Any) {
        this.representation = representation
    }

    override fun withNullRepresentation(representation: Any) {
        this.nullRepresentation = representation
    }

    override fun withReporter(reporter: Reporter) {
        this.reporter = reporter
    }

    fun build(): ExpectOptions = ExpectOptions(description, representation, nullRepresentation, reporter)
}
