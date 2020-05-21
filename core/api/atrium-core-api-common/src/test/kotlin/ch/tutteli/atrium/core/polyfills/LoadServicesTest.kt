package ch.tutteli.atrium.core.polyfills

import ch.tutteli.atrium.api.infix.en_GB.*
import ch.tutteli.atrium.api.verbs.internal.expect
import kotlin.test.Test

class LoadServicesTest {
    @Test
    fun noServiceFound_EmptySequence() {
        expect(loadServices(LoadServicesTest::class).toList()) toBe empty
    }

    @Test
    fun oneServiceFound_ReturnsTheService() {
        expect(loadServices(InterfaceWithOneImplementation::class)).asIterable {
            it containsExactly { isA<SingleService>() }
        }
    }

    @Test
    fun twoServicesFound_ThrowsIllegalStateException() {
        expect(loadServices(InterfaceWithTwoImplementation::class)) asIterable o contains o inAny order but only the entries(
            { isA<Service1>() },
            { isA<Service2>() }
        )
        expect {
            loadSingleService(InterfaceWithTwoImplementation::class)
        }.toThrow<IllegalStateException> {
            this messageContains values(
                "Found more than one implementation ",
                Service1::class.fullName,
                Service2::class.fullName
            )
        }
    }
}
