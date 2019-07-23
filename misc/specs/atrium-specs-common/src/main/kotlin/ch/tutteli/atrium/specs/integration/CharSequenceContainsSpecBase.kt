package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.specs.format
import ch.tutteli.atrium.specs.lineSeperator
import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root

abstract class CharSequenceContainsSpecBase(spec: Root.() -> Unit) : Spek(spec) {

    companion object {
        const val text = "Hello my name is Robert"
        const val helloWorld = "Hello World, I am Oskar"

        val containsDescr = DescriptionCharSequenceAssertion.CONTAINS.getDefault()
        val containsIgnoringCase = String.format(DescriptionCharSequenceAssertion.IGNORING_CASE.getDefault(), DescriptionCharSequenceAssertion.CONTAINS.getDefault())
        val numberOfOccurrences = DescriptionCharSequenceAssertion.NUMBER_OF_OCCURRENCES.getDefault()
        val value = DescriptionCharSequenceAssertion.VALUE.getDefault()
        val stringMatchingRegex = DescriptionCharSequenceAssertion.STRING_MATCHING_REGEX.getDefault()

        val atLeast = DescriptionCharSequenceAssertion.AT_LEAST.getDefault()
        val atMost = DescriptionCharSequenceAssertion.AT_MOST.getDefault()

        val illegalArgumentException = IllegalArgumentException::class.simpleName
        val separator = lineSeperator
    }
}
