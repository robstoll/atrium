package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThan
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThan
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import ch.tutteli.atrium.api.verbs.internal.testFactory
import kotlin.test.Test

class BasicJvmTest {

    @TestFactory
    fun group1() = testFactory {
        describe("some context") {
            it("calculates correctly") {
                expect(1).toEqual(1)
            }
            it("and another group") {
                expect(2) {
                    toBeLessThan(3)
                    toBeGreaterThan(1)
                }
            }
        }
    }

    @TestFactory
    fun group2() = testFactory({
        describe("some context in the first factory") {
            it("calculates correctly") {
                expect(1).toEqual(1)
            }
            it("and another group") {
                expect(2) {
                    toBeLessThan(3)
                    toBeGreaterThan(1)
                }
            }
        }
    }, {
        describe("some context in the second factory") {
            it("calculates correctly") {
                expect(1).toEqual(1)
            }
            it("and another group") {
                expect(2).toBeLessThan(3).toBeGreaterThan(1)
            }
        }
    })


    @Test
    fun can_still_define_simple_test() {
        expect(1).toEqual(1)
    }
}

