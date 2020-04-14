package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class AtriumPropertyTest {

    @Test
    fun get_undefined_returnsNull() {
        expect(getAtriumProperty("notYetDefined")) toBe null
    }

    @Test
    fun get_defined_returnsDefinedValue() {
        //arrange
        setAtriumProperty("a", "b")
        //act & assert
        expect(getAtriumProperty("a")) toBe "b"
    }

    @Test
    fun set_undefined_definedAfterwards() {
        //act
        setAtriumProperty("a", "b")
        //assert
        expect(getAtriumProperty("a")) toBe "b"
    }

    @Test
    fun set_defined_overwrites() {
        //arrange
        setAtriumProperty("a", "b")
        //act
        setAtriumProperty("a", "c")
        //assert
        expect(getAtriumProperty("a")) toBe "c"
    }
}
