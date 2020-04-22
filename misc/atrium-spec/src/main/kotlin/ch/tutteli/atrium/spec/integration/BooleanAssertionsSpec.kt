// TODO remove file with 1.0.0
@file:Suppress("DEPRECATION", "TYPEALIAS_EXPANSION_DEPRECATION")

package ch.tutteli.atrium.spec.integration

import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.creating.Assert
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.prefixedDescribe
import ch.tutteli.atrium.translations.DescriptionAnyAssertion
import ch.tutteli.atrium.translations.DescriptionBasic.TO_BE
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.include

@Deprecated("Switch from Assert to Expect and use Spec from atrium-specs-common; will be removed with 1.0.0")
abstract class BooleanAssertionsSpec(
    verbs: AssertionVerbFactory,
    toBeTruePair: Pair<String, Assert<Boolean>.() -> Assert<Boolean>>,
    toBeFalsePair: Pair<String, Assert<Boolean>.() -> Assert<Boolean>>,
    describePrefix: String = "[Atrium] "
) : Spek({

    include(@Suppress("DEPRECATION") object : SubjectLessAssertionSpec<Boolean>(describePrefix,
        toBeTruePair.first to mapToCreateAssertion { toBeTruePair.second(this) },
        toBeFalsePair.first to mapToCreateAssertion { toBeFalsePair.second(this) }
    ) {})

    include(@Suppress("DEPRECATION") object : CheckingAssertionSpec<Boolean>(verbs, describePrefix,
        checkingTriple(toBeTruePair.first, { toBeTruePair.second(this) }, true, false),
        checkingTriple(toBeFalsePair.first, { toBeFalsePair.second(this) }, false, true)
    ) {})

    fun prefixedDescribe(description: String, body: SpecBody.() -> Unit) {
        prefixedDescribe(describePrefix, description, body)
    }

    val assert: (Boolean) -> Assert<Boolean> = verbs::checkImmediately
    val expect = verbs::checkException
    val (toBeTrue, toBeTrueFun) = toBeTruePair
    val (toBeFalse, toBeFalseFun) = toBeFalsePair

    prefixedDescribe("subject is `true`") {
        val fluent = assert(true)
        test("$toBeTrue does not throw") {
            fluent.toBeTrueFun()
        }
        test("$toBeFalse throws an AssertionError containing ${DescriptionAnyAssertion::class.simpleName}.$TO_BE and `: false`") {
            expect {
                fluent.toBeFalseFun()
            }.toThrow<AssertionError> { messageContains("${TO_BE.getDefault()}: false") }
        }
    }

    prefixedDescribe("subject is `false`") {
        val fluent = assert(false)

        test("$toBeTrue throws an AssertionError containing ${DescriptionAnyAssertion::class.simpleName}.$TO_BE and `: true`") {
            expect {
                fluent.toBeTrueFun()
            }.toThrow<AssertionError> { messageContains("${TO_BE.getDefault()}: true") }
        }
        test("$toBeFalse does not throw") {
            fluent.toBeFalseFun()
        }
    }
})
