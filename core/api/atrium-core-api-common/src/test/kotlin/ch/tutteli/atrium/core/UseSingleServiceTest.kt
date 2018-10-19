package ch.tutteli.atrium.core

import ch.tutteli.atrium.api.cc.infix.en_GB.Values
import ch.tutteli.atrium.api.cc.infix.en_GB.isA
import ch.tutteli.atrium.api.cc.infix.en_GB.messageContains
import ch.tutteli.atrium.api.cc.infix.en_GB.toThrow
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.core.polyfills.useSingleService
import ch.tutteli.atrium.verbs.internal.assert
import ch.tutteli.atrium.verbs.internal.expect
import kotlin.test.Test

class UseSingleServiceTest {
    @Test
    fun emptyIterator_ThrowsNoSuchElementException() {
        expect {
            useSingleService(InterfaceWithOneImplementation::class, listOf<InterfaceWithOneImplementation>().iterator())
        }.toThrow<NoSuchElementException> {
            this messageContains Values(
                "Could not find any implementation",
                InterfaceWithOneImplementation::class.fullName
            )
        }
    }

    @Test
    fun oneServiceFound_ReturnsTheService() {
        val service = useSingleService(InterfaceWithOneImplementation::class, listOf(SingleService()).iterator())
        assert(service).isA<SingleService> { }
    }

    @Test
    fun twoServiceFound_ReturnsTheService() {
        expect {
            useSingleService(InterfaceWithTwoImplementation::class, listOf(Service1(), Service2()).iterator())
        }.toThrow<IllegalStateException> {
            this messageContains Values(
                "Found more than one implementation ",
                Service1::class.fullName,
                Service2::class.fullName
            )
        }
    }
}

