package ch.tutteli.atrium.spec.reporting.translating

import ch.tutteli.atrium.reporting.translating.ISimpleTranslatable
import ch.tutteli.atrium.reporting.translating.ITranslatable
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.toBe
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it

open class TranslatorSpec(
    val verbs: IAssertionVerbFactory,
    val testeeFactory: () -> ITranslator
) : Spek({
    val testee = testeeFactory()

    val translatableTest = object : ISimpleTranslatable {
        override val value = "test"
    }
    val translatableHello = object : ISimpleTranslatable {
        override val value = "hello"
    }

    context("no translations provided at all") {
        it("uses ${ITranslatable::class.simpleName}'s ${ITranslatable::getDefault.name}") {
            val result = testee.translate(translatableTest)
            verbs.checkImmediately(result).toBe(translatableTest.value)
        }
    }
    context("translations provided") {
        val translation = "bonjour"
        testee.add(translatableHello, translation)

        it("uses ${ITranslatable::class.simpleName}'s ${ITranslatable::getDefault.name} if not for the desired translatable"){
            val result = testee.translate(translatableTest)
            verbs.checkImmediately(result).toBe(translatableTest.value)
        }

        it("uses the translation if defined the desired translatable"){
            val result = testee.translate(translatableHello)
            verbs.checkImmediately(result).toBe(translation)
        }

        action("translation redefined"){
            val newTranslation = "ciao"
            testee.add(translatableHello, newTranslation)
            it("uses the new translation"){
                val result = testee.translate(translatableHello)
                verbs.checkImmediately(result).toBe(newTranslation)
            }
        }
    }

})
