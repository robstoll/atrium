package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.core.polyfills.format
import ch.tutteli.atrium.specs.lineSeparator
import ch.tutteli.atrium.translations.DescriptionCharSequenceExpectation
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root

abstract class CharSequenceToContainSpecBase(spec: Root.() -> Unit) : Spek(spec) {

    companion object {
        val helloMyNameIsRobert: CharSequence = "Hello my name is Robert"
        val helloWorld: CharSequence = "Hello World, I am Oskar"

        val toContainDescr = DescriptionCharSequenceExpectation.TO_CONTAIN.getDefault()
        val toContainIgnoringCase = DescriptionCharSequenceExpectation.IGNORING_CASE.getDefault().format(
            DescriptionCharSequenceExpectation.TO_CONTAIN.getDefault()
        )
        val numberOfOccurrences = DescriptionCharSequenceExpectation.NUMBER_OF_MATCHES.getDefault()
        val value = DescriptionCharSequenceExpectation.VALUE.getDefault()
        val stringMatchingRegex = DescriptionCharSequenceExpectation.STRING_MATCHING_REGEX.getDefault()
        val noMatchFoundDescr = DescriptionCharSequenceExpectation.NOT_FOUND.getDefault()

        val atLeast = DescriptionCharSequenceExpectation.AT_LEAST.getDefault()
        val atMost = DescriptionCharSequenceExpectation.AT_MOST.getDefault()

        val illegalArgumentException = IllegalArgumentException::class.simpleName
        val separator = lineSeparator
    }
}
