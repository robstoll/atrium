package ch.tutteli.atrium.core

import ch.tutteli.atrium.api.cc.infix.en_GB.Values
import ch.tutteli.atrium.api.cc.infix.en_GB.isA
import ch.tutteli.atrium.api.cc.infix.en_GB.messageContains
import ch.tutteli.atrium.api.cc.infix.en_GB.toThrow
import ch.tutteli.atrium.core.polyfills.fullName
import ch.tutteli.atrium.core.polyfills.loadSingleService
import ch.tutteli.atrium.verbs.internal.assert
import ch.tutteli.atrium.verbs.internal.expect
import kotlin.test.Test

class LoadSingleServiceTest {
    @Test
    fun noServiceFound_ThrowsNoSuchElementException() {
        expect {
            loadSingleService(LoadSingleServiceTest::class)
        }.toThrow<NoSuchElementException> {
            this messageContains Values("Could not find any implementation", LoadSingleServiceTest::class.fullName)
        }
    }

    @Test
    fun oneServiceFound_ReturnsTheService() {
        val service = loadSingleService(InterfaceWithOneImplementation::class)
        assert(service).isA<SingleService> { }
    }

    @Test
    fun twoServicesFound_ThrowsIllegalStateException() {
        expect {
            loadSingleService(InterfaceWithTwoImplementation::class)
        }.toThrow<IllegalStateException> {
            this messageContains Values(
                "Found more than one implementation ",
                Service1::class.fullName,
                Service2::class.fullName
            )
        }
    }
}
