package ch.tutteli.atrium.core

import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class DummyTest {
    @Test
    fun foo(){
        expect(1).toEqual(1)
    }
}
