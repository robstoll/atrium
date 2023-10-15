package ch.tutteli.atrium.api.infix.en_GB.samples

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import ch.tutteli.atrium.api.verbs.expectGrouped
import kotlin.test.Test

class GroupingSamples {

    @Test
    fun group() {
        fails {

            // You can use `expectGrouped` instead of `expect` in case you have multiple
            // unrelated subjects but want to evaluate/report them together.
            // Use independent `expect` if you want fail fast behaviour instead.
            expectGrouped {

                // you can state multiple `expect` within this ExpectGrouping-block where all `expect` and `group`
                // inside are evaluated together; similar to an expectation-group block.

                val someProperty = 1
                expect(someProperty) toBeGreaterThan 5 toBeLessThan 0
                //                   | fails           |
                //                                     | still evaluated since we are within an ExpectGrouping-block
                //                                       in contrast to a standalone expect where toBeGreaterThan
                //                                       would have failed fast

                // ... however, this `expect` is still evaluated even though the first `expect` failed
                expect(1) {
                    // now we are within an expectation-group block. In contrast to an ExpectGrouping-block,
                    // we already defined a subject (2) for which we want to state multiple expectations.
                    // Likewise, they are evaluated together:

                    it toBeGreaterThan 5 // fails
                    it toBeLessThan 0    // still evaluated, fails as well
                }

                // this group is still evaluated even though the first two `expect` failed
                group("verifying basic properties") {
                    // also all expect within a group are evaluated together

                    expect(1) toEqual 2
                    // imagine multiple expect within this group,
                    // they are all evaluated even though the first in this group already failed

                    // you can nest groups as often as you like
                    group("sub-group") {
                        //...
                    }
                }

                // another group which is still evaluated despite all the failures above
                group("verifying edge cases") {
                    //...
                }

                expect(2)
                    // you can also use group to structure reporting
                    .group("first group") {
                        it toBeGreaterThan 5 // fails
                        it toBeLessThan 20   // still evaluated, holds ...
                    } // ... the group as such failed though and since we have not used an
                    // expectation-group block for this expect, the following group is not evaluated:
                    .group("verifying failing cases") {
                        //...
                    }
            }

            // you can optionally change the default top-level group description
            expectGrouped("Verifying privileged actions") {
                //...
            }
        }
    }
}
