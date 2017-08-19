package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.assertions.DescriptionCharSequenceAssertion.AT_MOST
import ch.tutteli.atrium.assertions.charsequence.contains.CharSequenceContainsAssertionCreator
import ch.tutteli.atrium.assertions.charsequence.contains.builders.*
import ch.tutteli.atrium.assertions.charsequence.contains.decorators.CharSequenceContainsNoOpDecorator
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import java.util.regex.PatternSyntaxException
import kotlin.reflect.KProperty

object CharSequenceContainsRegexAssertionSpec : Spek({

    val text = "Hello my name is Robert"
    val hello = "[hH][ea]llo"
    val robert = "Roberto?"
    val fluent = assert(text)

    val containsProp: KProperty<CharSequenceContainsBuilder<String, CharSequenceContainsNoOpDecorator>> = fluent::contains
    val contains = containsProp.name
    val atLeast = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::atLeast.name
    val exactly = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::exactly.name
    val atMost = CharSequenceContainsBuilder<String, CharSequenceContainsAssertionCreator.IDecorator>::atMost.name
    val regex = CharSequenceContainsCheckerBuilder<String, CharSequenceContainsNoOpDecorator>::regex.name
    val ignoringCase = CharSequenceContainsBuilder<String, CharSequenceContainsNoOpDecorator>::ignoringCase.name

    describe("fun $contains with search mode $regex") {
        context("throws an ${PatternSyntaxException::class.simpleName}") {
            test("if an erroneous pattern is passed") {
                expect {
                    assert("a").contains.exactly(1).regex("notA(validPattern")
                }.toThrow<PatternSyntaxException>()
            }
            test("if an erroneous pattern is passed as second regex") {
                expect {
                    assert("a").contains.exactly(1).regex("h(a|e)llo", "notA(validPattern")
                }.toThrow<PatternSyntaxException>()
            }
        }

        context("text $text") {
            test("$contains '$hello' $atLeast once does not throw") {
                fluent.contains.atLeast(1).regex(hello)
            }

            test("$contains '$hello' and '$robert' $exactly once does not throw") {
                fluent.contains.atLeast(1).regex(hello, robert)
            }

            test("$contains '[a-z]' $atMost 17 times does not throw") {
                fluent.contains.atMost(17).regex("[a-z]")
            }
            test("$contains $ignoringCase '[a-z]' $atMost 19 times does not throw") {
                fluent.contains.ignoringCase.atMost(19).regex("[a-z]")
            }

            test("$contains '[a-z]' $atMost 16 times does not throw") {
                expect {
                    fluent.contains.atMost(16).regex("[a-z]")
                }.toThrow<AssertionError>().message.containsDefaultTranslationOf(AT_MOST)
            }
            test("$contains $ignoringCase '[a-z]' $atMost 18 times does not throw") {
                expect {
                    fluent.contains.ignoringCase.atMost(18).regex("[a-z]")
                }.toThrow<AssertionError>().message.containsDefaultTranslationOf(AT_MOST)
            }
        }
    }
})
