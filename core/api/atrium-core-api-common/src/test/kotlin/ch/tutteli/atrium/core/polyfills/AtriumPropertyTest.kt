package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.cc.infix.en_GB.toBe
import ch.tutteli.atrium.verbs.internal.assert
import kotlin.test.Test

class AtriumPropertyTest {

    @Test
    fun get_undefined_returnsNull() {
        assert(getAtriumProperty("notYetDefined")) toBe null
    }

    @Test
    fun get_defined_returnsDefinedValue() {
        //arrange
        setAtriumProperty("a", "b")
        //act & assert
        assert(getAtriumProperty("a")) toBe "b"
    }

    @Test
    fun set_undefined_definedAfterwards() {
        //act
        setAtriumProperty("a", "b")
        //assert
        assert(getAtriumProperty("a")) toBe "b"
    }

    @Test
    fun set_defined_overwrites() {
        //arrange
        setAtriumProperty("a", "b")
        //act
        setAtriumProperty("a", "c")
        //assert
        assert(getAtriumProperty("a")) toBe "c"
    }
}
