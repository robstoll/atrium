package ch.tutteli.atrium.api.fluent.en_GB.samples

import ch.tutteli.atrium.api.fluent.en_GB.*
import ch.tutteli.atrium.api.verbs.expect
import java.sql.SQLException
import kotlin.test.Test

class SQLExceptionFeaturesSamples {

    @Test
    fun errorCodeFeature() {
        val exception = SQLException("Test exception", "42000", 1001)

        expect(exception)
            .errorCode                                            // subject is now of type Int
            .toEqual(1001)

        fails {
            expect(exception)
                .errorCode                                        // subject is now of type Int
                .toEqual(9999)                           // fails
                .toBeGreaterThan(1000)                   // not evaluated/reported because `toEqual` already fails
        //                                                        use `.errorCode { ... }` if you want all assertions evaluated
        }
    }

    @Test
    fun errorCode() {
        val exception = SQLException("Test exception", "42000", 1001)

        expect(exception).errorCode {               // subject within this expectation-group is of type Int
            toBeGreaterThan(1000)
            toEqual(1001)
        }                                                         // subject here is back to type SQLException

        fails {
            expect(exception).errorCode {
                toEqual(9999)                            // fails
                toBeGreaterThan(1000)                    // still evaluated even though `toEqual` already fails
            }
        }
    }

    @Test
    fun sqlStateFeature() {
        val exception = SQLException("Test exception", "42000", 1001)

        expect(exception)
            .sqlState                                           // subject is now of type String
            .toEqual("42000")

        fails {
            expect(exception)
                .sqlState                                       // subject is now of type String
                .toEqual("00000")                      // fails
                .toContain("42")                       // not evaluated/reported because `toEqual` already fails
            //                                                  use `.sqlState { ... }` if you want all assertions evaluated
        }
    }



    @Test
    fun sqlState() {
        val exception = SQLException("Test exception", "42000", 1001)

        expect(exception).sqlState {           // subject within this expectation-group is of type String
            toEqual("42000")
            toContain("42")
        }                                                      // subject here is back to type SQLException

        fails {
            expect(exception).sqlState {
                toEqual("00000")                      // fails
                toContain("42")                       // still evaluated even though `toEqual` already fails
            }
        }
    }
}
