package ch.tutteli.atrium.testfactories

import ch.tutteli.atrium.api.fluent.en_GB.toBeGreaterThan
import ch.tutteli.atrium.api.fluent.en_GB.toBeLessThan
import ch.tutteli.atrium.api.fluent.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.testFactory
import ch.tutteli.atrium.specs.toBeGreaterThanDescr

class BasicJvmTest {

    @TestFactory
    fun group() = testFactory {
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
}

