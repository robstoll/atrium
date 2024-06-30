package ch.tutteli.atrium.specs.integration

import ch.tutteli.atrium.creating.Expect
import ch.tutteli.atrium.reporting.reportables.descriptions.DescriptionCharSequenceProof
import ch.tutteli.atrium.specs.lineSeparator
import org.spekframework.spek2.Spek
import org.spekframework.spek2.dsl.Root

abstract class CharSequenceToContainSpecBase(spec: Root.() -> Unit) : Spek(spec) {

    companion object {
        val text: CharSequence = "Hello my name is Robert"
        val helloWorld: CharSequence = "Hello World, I am Oskar"
        val illegalArgumentException = IllegalArgumentException::class.simpleName

        const val ERROR_MESSAGE_ONLY_CHARSEQUENCE_NUMBER_CHAR = "Only values of type CharSequence, Number and Char are allowed"

        fun Expect<String>.toContainValue(representation: Any?) =
            toContainDescr(DescriptionCharSequenceProof.VALUE, representation)

        fun Expect<String>.toContainNumberOfMatches(representation: Any?, numOfMatches: Int = 1) =
            toContainDescr(DescriptionCharSequenceProof.NUMBER_OF_MATCHES, representation, numOfMatches)
    }
}
