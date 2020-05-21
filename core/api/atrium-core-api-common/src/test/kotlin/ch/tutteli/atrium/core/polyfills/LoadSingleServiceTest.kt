package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class LoadSingleServiceTest {
    @Test
    fun noServiceFound_ThrowsNoSuchElementException() {
        expect {
            loadSingleService(LoadSingleServiceTest::class)
        }.toThrow<NoSuchElementException> {
            it messageContains values("Could not find any implementation", LoadSingleServiceTest::class.fullName)
        }
    }

    @Test
    fun oneServiceFound_ReturnsTheService() {
        val service = loadSingleService(InterfaceWithOneImplementation::class)
        expect(service).isA<SingleService>()
    }

    @Test
    fun twoServicesFound_ThrowsIllegalStateException() {
        expect {
            loadSingleService(InterfaceWithTwoImplementation::class)
        }.toThrow<IllegalStateException> {
            its messageContains values(
                "Found more than one implementation ",
                Service1::class.fullName,
                Service2::class.fullName
            )
        }
    }
}
