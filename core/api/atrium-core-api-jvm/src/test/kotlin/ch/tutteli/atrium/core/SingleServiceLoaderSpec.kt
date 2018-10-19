package ch.tutteli.atrium.core

import ch.tutteli.atrium.api.cc.infix.en_GB.*
import ch.tutteli.atrium.verbs.internal.assert
import ch.tutteli.atrium.verbs.internal.expect
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it

object SingleServiceLoaderSpec : Spek({

    given("no service") {
        it("throws an NoSuchElementException") {
            expect {
                SingleServiceLoader.load(SingleServiceLoaderSpec::class.java)
            }.toThrow<NoSuchElementException> {
                message {
                    this contains Values("Could not find any implementation", SingleServiceLoaderSpec::class.java.name)
                }
            }
        }
    }

    given("single service") {
        it("loads the corresponding implementation") {
            val service = SingleServiceLoader.load(InterfaceWithOneImplementation::class.java)
            assert(service).isA<A> {  }
        }
    }

    given("more than one service") {
        it("throws an IllegalStateException") {
            expect {
                SingleServiceLoader.load(InterfaceWithTwoImplementation::class.java)
            }.toThrow<IllegalStateException> {
                this messageContains Values(
                    "Found more than one implementation ",
                    A1::class.java.name,
                    A2::class.java.name
                )
            }
        }
    }
})

interface InterfaceWithOneImplementation
class A: InterfaceWithOneImplementation

interface InterfaceWithTwoImplementation
class A1: InterfaceWithTwoImplementation
class A2: InterfaceWithTwoImplementation
