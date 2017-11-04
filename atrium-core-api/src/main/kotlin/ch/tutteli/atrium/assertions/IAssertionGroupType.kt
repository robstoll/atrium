package ch.tutteli.atrium.assertions

/**
 * Represents a type of an [IAssertionGroup].
 *
 * This interface is essentially a marker interface and should be sub-typed by other interfaces.
 *
 * As side notice: I decided to use a marker interface instead of an `enum class` -- which is not open for
 * extension -- so that an unknown number of types can be created without the need to modify existing implementations.
 */
interface IAssertionGroupType : IBulletPointIdentifier
