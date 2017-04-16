package ch.tutteli.atrium

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object CollectionAssertionsSpec : Spek({
    val fluent = assert(listOf(1, 2))
    describe("fun ${fluent::hasSize.name}") {
        context("collection with two entries") {
            test("expect 2 does not throw") {
                fluent.hasSize(2)
            }
            test("expect 1 throws an AssertionError") {
                expect {
                    fluent.hasSize(1)
                }.toThrow<AssertionError>()
            }
            test("expect 3 throws an AssertionError") {
                expect {
                    fluent.hasSize(3)
                }.toThrow<AssertionError>()
            }
        }
    }

    describe("fun ${fluent::isEmpty.name}") {
        it("does not throw if a collection is empty") {
            assert(listOf<Int>()).isEmpty()
        }

        it("throws an AssertionError if a collection is not empty") {
            expect {
                assert(listOf(1, 2)).isEmpty()
            }.toThrow<AssertionError>()
        }
    }
})
