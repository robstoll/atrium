package ch.tutteli.atrium.spec.assertions

import ch.tutteli.atrium.assertions.DescriptionAnyAssertion.*
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe

object AnyAssertionsSpec : Spek({
    data class DataClass(val isWhatever: Boolean)

    val toBe = "toBe"
    val notToBe = "notToBe"
    val isSame = "isSame"
    val isNotSame = "isNotSame"
    describe("fun $toBe, $notToBe, $isSame and $isNotSame") {
        context("primitive") {
            context("one equals the other") {
                test("$toBe does not throw") {
                    assert(1).toBe(1)
                }
                test("$isSame does not throw") {
                    assert(1).isSame(1)
                }
                test("$notToBe throws AssertionError") {
                    expect {
                        assert(1).notToBe(1)
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(NOT_TO_BE)
                }
                test("$isNotSame throws AssertionError") {
                    expect {
                        assert(1).isNotSame(1)
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(IS_NOT_SAME)
                }
            }
            context("one does not equal the other") {
                test("$toBe throws AssertionError") {
                    expect {
                        assert(1).toBe(2)
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(TO_BE)
                }
                test("$notToBe does not throw") {
                    assert(1).notToBe(2)
                }
                test("$isSame throws AssertionError") {
                    expect {
                        assert(1).isSame(2)
                    }.toThrow<AssertionError>().and.message.containsDefaultTranslationOf(IS_SAME)
                }
                test("$isNotSame does not throw") {
                    assert(1).isNotSame(2)
                }
            }
        }
        context("class") {
            val test = DataClass(true)
            val fluent = assert(test)
            context("same") {
                test("$toBe does not throw") {
                    fluent.toBe(test)
                }
                test("$notToBe throws AssertionError") {
                    expect {
                        fluent.notToBe(test)
                    }.toThrow<AssertionError>()
                }
                test("$isSame does not throw") {
                    fluent.isSame(test)
                }
                test("$isNotSame throws AssertionError") {
                    expect {
                        fluent.isNotSame(test)
                    }.toThrow<AssertionError>()
                }
            }
            context("not same but one equals the other") {
                val other = DataClass(true)
                test("$toBe does not throw") {
                    fluent.toBe(other)
                }
                test("$notToBe throws AssertionError") {
                    expect {
                        fluent.notToBe(other)
                    }.toThrow<AssertionError>()
                }
                test("$isSame throws AssertionError") {
                    expect {
                        fluent.isSame(other)
                    }.toThrow<AssertionError>()
                }
                test("$isNotSame does not throw") {
                    fluent.isNotSame(other)
                }
            }
            context("one does not equal the other") {
                val other = DataClass(false)
                test("$toBe does not throw") {
                    expect {
                        fluent.toBe(other)
                    }.toThrow<AssertionError>()
                }
                test("$notToBe throws AssertionError") {
                    fluent.notToBe(other)
                }
                test("$isSame throws AssertionError") {
                    expect {
                        fluent.isSame(other)
                    }.toThrow<AssertionError>()
                }
                test("$isNotSame does not throw") {
                    fluent.isNotSame(other)
                }
            }
        }
    }
})
