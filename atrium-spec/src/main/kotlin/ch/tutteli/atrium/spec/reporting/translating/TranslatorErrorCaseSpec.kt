package ch.tutteli.atrium.spec.reporting.translating

import ch.tutteli.atrium.api.cc.en_UK.contains
import ch.tutteli.atrium.api.cc.en_UK.message
import ch.tutteli.atrium.api.cc.en_UK.toThrow
import ch.tutteli.atrium.reporting.translating.ITranslator
import ch.tutteli.atrium.spec.IAssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import java.util.*

abstract class TranslatorErrorCaseSpec(
    verbs: IAssertionVerbFactory,
    testeeFactory: (locale: Locale, fallbackLocals: Array<out Locale>) -> ITranslator,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit)
        = describeFun(describePrefix, funName, body = body)

    fun testeeFactory(locale: Locale, vararg fallbackLocals: Locale)
        = testeeFactory(locale, fallbackLocals)

    describeFun(ITranslator::translate.name) {

        listOf(
            Locale("no"),
            Locale("no", "NO"),
            Locale("no", "NO", "NY"),
            Locale("no", "NO", "B"),
            Locale("no", "ZZ")
        ).forEach { locale ->
            context("primary Locale's language is $locale") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    verbs.checkException {
                        testeeFactory(locale)
                    }.toThrow<IllegalArgumentException> { message { contains("The macrolanguage `no` is not supported", locale.toString()) } }
                }
            }

            context("first fallback Locale is $locale") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    verbs.checkException {
                        testeeFactory(Locale.UK, locale)
                    }.toThrow<IllegalArgumentException> { message { contains("The macrolanguage `no` is not supported", locale.toString()) } }
                }
            }

            context("second fallback Locale is $locale") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    verbs.checkException {
                        testeeFactory(Locale.UK, Locale.FRENCH, locale)
                    }.toThrow<IllegalArgumentException> { message { contains("The macrolanguage `no` is not supported", locale.toString()) } }
                }
            }
        }

        val builder = Locale.Builder().setLanguage("zh")
        listOf("Hant", "Hans").forEach { script ->
            val locale = builder.setScript(script).build()
            context("primary Locale's language is $locale") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    verbs.checkException {
                        testeeFactory(locale)
                    }.toThrow<IllegalArgumentException> { message { contains("Script `$script` for Locale with language `zh` is not supported.") } }
                }
            }
            context("first fallback Locale is $locale") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    verbs.checkException {
                        testeeFactory(Locale.UK, locale)
                    }.toThrow<IllegalArgumentException> { message { contains("Script `$script` for Locale with language `zh` is not supported.") } }
                }
            }

            context("second fallback Locale is $locale") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    verbs.checkException {
                        testeeFactory(Locale.UK, Locale.FRENCH, locale)
                    }.toThrow<IllegalArgumentException> { message { contains("Script `$script` for Locale with language `zh` is not supported.") } }
                }
            }
        }

        describe("exceptions for zh_...") {

            listOf("Hant", "Hans").forEach { script ->
                it("does not throw if Country is even though script is `$script`") {
                    val locale = builder.setRegion("ZZ").setScript(script).build()
                    testeeFactory(locale)
                }
            }

            it("does not throw if Country is empty and script is neither `Hant` nor `Hans`") {
                val locale = builder.setScript("Tata").build()
                testeeFactory(locale)
            }
        }
    }
})
