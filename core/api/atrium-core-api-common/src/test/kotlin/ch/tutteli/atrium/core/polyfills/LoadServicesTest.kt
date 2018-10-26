package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.cc.infix.en_GB.*
import ch.tutteli.atrium.api.cc.infix.en_GB.keywords.Empty
import ch.tutteli.atrium.verbs.internal.assert
import ch.tutteli.atrium.verbs.internal.expect
import kotlin.test.Test

class LoadServicesTest {
    @Test
    fun noServiceFound_EmptySequence() {
        assert(loadServices(LoadServicesTest::class).toList()) toBe Empty
    }

    @Test
    fun oneServiceFound_ReturnsTheService() {
        assert(loadServices(InterfaceWithOneImplementation::class)).asIterable().and {
            containsStrictly { isA<SingleService> {} }
        }
    }

    @Test
    fun twoServicesFound_ThrowsIllegalStateException() {
        assert(loadServices(InterfaceWithTwoImplementation::class)).asIterable() contains Entries(
            { isA<Service1> {} },
            { isA<Service2> {} }
        )
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
