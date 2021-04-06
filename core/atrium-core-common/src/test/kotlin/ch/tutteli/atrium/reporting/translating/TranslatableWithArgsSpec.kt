package ch.tutteli.atrium.reporting.translating

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.reporting.Text
import ch.tutteli.atrium.reporting.erroradjusters.NoOpAtriumErrorAdjuster
import ch.tutteli.atrium.reporting.translating.Translations.*
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class TranslatableWithArgsSpec : Spek({
    describe("creating a TranslatableWithArgs") {
        context("for a translatable without args") {
            it("no arguments given; throws") {
                expect {
                    TranslatableWithArgs(HELLO, listOf())
                }.toThrow<IllegalArgumentException> {
                    its messageContains values(
                        "No arguments specified"
                    )
                }
            }
            it("one argument given; throws") {
                expect {
                    TranslatableWithArgs(HELLO, "one")
                }.toThrow<IllegalArgumentException> {
                    its messageContains values(
                        "The number of given arguments does not match the number of placeholders in",
                        "number of arguments = 1",
                        "placeholders = 0"
                    )
                }
            }
        }
        context("for a translatable with 1 arg") {
            it("no arguments given; throws") {
                expect {
                    TranslatableWithArgs(WITH_1_ARG, listOf())
                }.toThrow<IllegalArgumentException> {
                    its messageContains values(
                        "No arguments specified"
                    )
                }
            }
            it("one argument given; OK") {
                expect(TranslatableWithArgs(WITH_1_ARG, "hello").getDefault()).toBe("a) hello")
                expect(TranslatableWithArgs(WITH_1_ARG, listOf("hello")).getDefault()).toBe("a) hello")
            }
            it("two arguments given; throws") {
                expect {
                    TranslatableWithArgs(WITH_1_ARG, "one", "two")
                }.toThrow<IllegalArgumentException> {
                    its messageContains values(
                        "The number of given arguments does not match the number of placeholders in",
                        "number of arguments = 2",
                        "placeholders = 1"
                    )
                }
            }
        }
        context("for a translatable with 2 args") {
            it("no arguments given; throws") {
                expect {
                    TranslatableWithArgs(WITH_2_ARGS, listOf())
                }.toThrow<IllegalArgumentException> {
                    its messageContains values(
                        "No arguments specified"
                    )
                }
            }
            it("one argument given; throws") {
                expect {
                    TranslatableWithArgs(WITH_2_ARGS, "one")
                }.toThrow<IllegalArgumentException> {
                    its messageContains values(
                        "The number of given arguments does not match the number of placeholders in",
                        "number of arguments = 1",
                        "placeholders = 2"
                    )
                }
            }
            it("two arguments given; OK") {
                expect(TranslatableWithArgs(WITH_2_ARGS, "hello", "world").getDefault()).toBe("a) hello b) world")
                expect(TranslatableWithArgs(WITH_2_ARGS, listOf("hello", "world")).getDefault())
                    .toBe("a) hello b) world")
            }
        }
    }
})

private enum class Translations(override val value: String) : StringBasedTranslatable {
    HELLO("hello"),
    WITH_1_ARG("a) %s"),
    WITH_2_ARGS("a) %s b) %s"),

}
