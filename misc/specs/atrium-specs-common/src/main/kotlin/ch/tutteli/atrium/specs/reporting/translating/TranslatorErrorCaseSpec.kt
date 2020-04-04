package ch.tutteli.atrium.specs.reporting.translating

import ch.tutteli.atrium.api.fluent.en_GB.messageContains
import ch.tutteli.atrium.api.fluent.en_GB.toThrow
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.reporting.translating.Locale
import ch.tutteli.atrium.reporting.translating.Translator
import ch.tutteli.atrium.specs.describeFunTemplate
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.Suite

abstract class TranslatorErrorCaseSpec(
    testeeFactory: (locale: Locale, fallbackLocals: List<Locale>) -> Translator,
    describePrefix: String = "[Atrium] "
) : Spek({

    fun describeFun(vararg funName: String, body: Suite.() -> Unit) =
        describeFunTemplate(describePrefix, funName, body = body)

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
                    expect {
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
                    expect {
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
                    expect {
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
                    expect {
                        testeeFactory(locale)
                    }
                        .toThrow<IllegalArgumentException> { messageContains("Script `$script` for Locale with language `zh` is not supported.") }
                }
            }
            context("first fallback Locale is $locale") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    expect {
                        testeeFactory(localeGb, locale)
                    }
                        .toThrow<IllegalArgumentException> { messageContains("Script `$script` for Locale with language `zh` is not supported.") }
                }
            }

            context("second fallback Locale is $locale") {
                it("throws an ${IllegalArgumentException::class.simpleName}") {
                    expect {
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
