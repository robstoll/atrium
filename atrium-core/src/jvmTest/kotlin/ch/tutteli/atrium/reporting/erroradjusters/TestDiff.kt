package ch.tutteli.atrium.reporting.erroradjusters

import ch.tutteli.atrium.api.infix.en_GB.toEqual
import ch.tutteli.atrium.api.verbs.internal.expect
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class TestDiff {

    @Test
    fun foo(){
        expect(1).toEqual(2)
    }

    @Test
    fun bar(){
        assertEquals("""
        hello

        ====== diff powered by Atrium
        """.trimIndent(), """
        world

        ====== diff powered by Atrium
        """.trimIndent())
    }
}
