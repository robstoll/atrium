package ch.tutteli.atrium.spec.reporting.translating

import ch.tutteli.atrium.api.cc.en_GB.messageContains
import ch.tutteli.atrium.api.cc.en_GB.toThrow
import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.spec.AssertionVerbFactory
import ch.tutteli.atrium.spec.describeFun
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.SpecBody
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

//TODO #116 migrate spek1 to spek2 - move to specs-common
abstract class TranslatorErrorCaseSpec(
    verbs: AssertionVerbFactory,
    testeeFactory: (locale: Locale, fallbackLocals: List<Locale>) -> Translator,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: SpecBody.() -> Unit) =
        describeFun(describePrefix, funName, body = body)

    fun testeeFactory(locale: Locale, vararg fallbackLocals: Locale) = testeeFactory(locale, fallbackLocals.toList())

    val localeGb = Locale("en", "GB")
    val localeFr = Locale("fr")

    describeFun(Translator::translate.name) {
        listOf(
            Locale("no"),
            Locale("no", "NO"),
            Locale("no", null, "NO", "NY"),
            Locale("no", null, "NO", "B"),
            Locale("no", "ZZ")
        ).forEach { locale ->
            context("primary Locale's language is $locale") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    verbs.checkException {
                        testeeFactory(locale)
                    }.toThrow<IllegalArgumentException> {
                        messageContains(
                            "The macrolanguage `no` is not supported",
                            locale.toString()
                        )
                    }
                }
            }

            context("first fallback Locale is $locale") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    verbs.checkException {
                        testeeFactory(localeGb, locale)
                    }.toThrow<IllegalArgumentException> {
                        messageContains(
                            "The macrolanguage `no` is not supported",
                            locale.toString()
                        )
                    }
                }
            }

            context("second fallback Locale is $locale") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    verbs.checkException {
                        testeeFactory(localeGb, localeFr, locale)
                    }.toThrow<IllegalArgumentException> {
                        messageContains(
                            "The macrolanguage `no` is not supported",
                            locale.toString()
                        )
                    }
                }
            }
        }

        listOf("Hant", "Hans").forEach { script ->
            val locale = Locale("zh", script, null, null)
            context("primary Locale's language is $locale") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    verbs.checkException {
                        testeeFactory(locale)
                    }
                        .toThrow<IllegalArgumentException> { messageContains("Script `$script` for Locale with language `zh` is not supported.") }
                }
            }
            context("first fallback Locale is $locale") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    verbs.checkException {
                        testeeFactory(localeGb, locale)
                    }
                        .toThrow<IllegalArgumentException> { messageContains("Script `$script` for Locale with language `zh` is not supported.") }
                }
            }

            context("second fallback Locale is $locale") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    verbs.checkException {
                        testeeFactory(localeGb, localeFr, locale)
                    }
                        .toThrow<IllegalArgumentException> { messageContains("Script `$script` for Locale with language `zh` is not supported.") }
                }
            }
        }

        describe("exceptions for zh_...") {

            listOf("Hant", "Hans").forEach { script ->
                it("does not throw if Country is even though script is `$script`") {
                    val locale = Locale("zh", script, "ZZ", null)
                    testeeFactory(locale)
                }
            }

            it("does not throw if Country is empty and script is neither `Hant` nor `Hans`") {
                val locale = Locale("zh", "Tata", null, null)
                testeeFactory(locale)
            }
        }
    }
})
