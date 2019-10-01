package ch.tutteli.atrium.api.verbs

import ch.tutteli.atrium.domain.builders.reporting.ExpectBuilder
import ch.tutteli.atrium.domain.builders.reporting.ExpectOptions
import ch.tutteli.atrium.reporting.RawString

/**
 * Optimised version which only creates ExpectOptions if really required.
 */
fun <T> ExpectBuilder.OptionsStep<T>.withMaybeRepresentationAndMaybeOptions(
    representation: String?, options: ExpectOptions?
): ExpectBuilder.FinalStep<T> =
    if (representation == null) {
        if (options == null) this.withoutOptions()
        else this.withOptions(options)
    } else {
        val representationOption = ExpectOptions(representation = RawString.create(representation))
        if (options == null) this.withOptions(representationOption)
        else this.withOptions(options.merge(representationOption))
    }
