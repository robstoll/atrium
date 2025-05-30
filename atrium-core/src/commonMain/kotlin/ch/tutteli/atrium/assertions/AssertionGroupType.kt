package ch.tutteli.atrium.assertions

/**
 * Represents a type of [AssertionGroup].
 *
 * This interface is essentially a marker interface and should be subtyped by other interfaces.
 *
 * As side notice: I decided to use a marker interface instead of an `enum class` -- which is not open for
 * extension -- so that an unknown number of types can be created without the need to modify existing implementations.
 */
@Deprecated("Switch from AssertionGroup to ProofGroup which does not require AssertionGroupType, will be removed with 2.0.0 at the latest")
interface AssertionGroupType : BulletPointIdentifier
