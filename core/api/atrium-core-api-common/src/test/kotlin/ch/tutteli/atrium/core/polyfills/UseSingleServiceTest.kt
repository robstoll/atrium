package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class UseSingleServiceTest {
    @Test
    fun emptyIterator_ThrowsNoSuchElementException() {
        expect {
            useSingleService(InterfaceWithOneImplementation::class, listOf<InterfaceWithOneImplementation>().iterator())
        }.toThrow<NoSuchElementException> {
            its messageContains values(
                "Could not find any implementation",
                InterfaceWithOneImplementation::class.fullName
            )
        }
    }

    @Test
    fun oneServiceFound_ReturnsTheService() {
        val service = useSingleService(InterfaceWithOneImplementation::class, listOf(SingleService()).iterator())
        expect(service).isA<SingleService>()
    }

    @Test
    fun twoServiceFound_ReturnsTheService() {
        expect {
            useSingleService(InterfaceWithTwoImplementation::class, listOf(Service1(), Service2()).iterator())
        }.toThrow<IllegalStateException> {
            its messageContains values(
                "Found more than one implementation ",
                Service1::class.fullName,
                Service2::class.fullName
            )
        }
    }
}

