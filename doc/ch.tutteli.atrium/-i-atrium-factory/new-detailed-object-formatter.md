[atrium](../../index.md) / [ch.tutteli.atrium](../index.md) / [IAtriumFactory](index.md) / [newDetailedObjectFormatter](.)

# newDetailedObjectFormatter

`abstract fun newDetailedObjectFormatter(): `[`IObjectFormatter`](../../ch.tutteli.atrium.reporting/-i-object-formatter/index.md) [(source)](https://github.com/robstoll/atrium/tree/master/atrium-api/src/main/kotlin/ch/tutteli/atrium/IAtriumFactory.kt#L162)

Creates an [IObjectFormatter](../../ch.tutteli.atrium.reporting/-i-object-formatter/index.md) which represents objects by using their [Object.toString](http://docs.oracle.com/javase/6/docs/api/java/lang/Object.html#toString()) representation
including [Class.name](#) and their [System.identityHashCode](http://docs.oracle.com/javase/6/docs/api/java/lang/System.html#identityHashCode(java.lang.Object)).

**Return**
The newly created object formatter.

