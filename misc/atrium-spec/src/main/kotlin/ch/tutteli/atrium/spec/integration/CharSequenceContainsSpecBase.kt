package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.translations.DescriptionCharSequenceAssertion
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.Spec

abstract class CharSequenceContainsSpecBase(spec: Spec.() -> Unit) : Spek(spec) {

    companion object {
        const val text = "Hello my name is Robert"
        const val helloWorld = "Hello World, I am Oskar"

        val containsDescr = DescriptionCharSequenceAssertion.CONTAINS.getDefault()
        val containsIgnoringCase = String.format(DescriptionCharSequenceAssertion.IGNORING_CASE.getDefault(), DescriptionCharSequenceAssertion.CONTAINS.getDefault())
        val numberOfOccurrences = DescriptionCharSequenceAssertion.NUMBER_OF_OCCURRENCES.getDefault()

        val atLeast = DescriptionCharSequenceAssertion.AT_LEAST.getDefault()
        val atMost = DescriptionCharSequenceAssertion.AT_MOST.getDefault()

        val illegalArgumentException = IllegalArgumentException::class.simpleName
        val separator = System.getProperty("line.separator")!!
    }
}
