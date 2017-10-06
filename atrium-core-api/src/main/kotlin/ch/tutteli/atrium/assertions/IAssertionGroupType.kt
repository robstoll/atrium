package ch.tutteli.atrium.assertions

/**
 * Represents an type of an [IAssertionGroup].
 *
 * It is essentially a marker interface and should sub-typed by other interfaces
 *
 * As side notice: instead of using an `enum class` -- which is not open for extensions -- we are using this interface
 * so that an unknown number of types can be created without the need to modify an implementation
 */
interface IAssertionGroupType
