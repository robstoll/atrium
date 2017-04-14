[atrium](../../index.md) / [ch.tutteli.atrium](../index.md) / [AtriumFactory](index.md) / [newDetailedObjectFormatter](.)

# newDetailedObjectFormatter

`fun newDetailedObjectFormatter(): `[`IObjectFormatter`](../../ch.tutteli.atrium.reporting/-i-object-formatter/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-impl-robstoll/src/main/kotlin/ch/tutteli/atrium/AtriumFactory.kt#L52)

Overrides [IAtriumFactory.newDetailedObjectFormatter](../-i-atrium-factory/new-detailed-object-formatter.md)

Creates an [IObjectFormatter](../../ch.tutteli.atrium.reporting/-i-object-formatter/index.md) which represents objects by using their [Object.toString](http://docs.oracle.com/javase/6/docs/api/java/lang/Object.html#toString()) representation
including [Class.name](#) and their [System.identityHashCode](http://docs.oracle.com/javase/6/docs/api/java/lang/System.html#identityHashCode(java.lang.Object)).

**Return**
The newly created object formatter.

