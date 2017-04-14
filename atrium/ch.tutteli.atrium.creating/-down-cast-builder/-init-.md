[atrium](../../index.md) / [ch.tutteli.atrium.creating](../index.md) / [DownCastBuilder](index.md) / [&lt;init&gt;](.)

# &lt;init&gt;

`DownCastBuilder(description: String, subClass: KClass<TSub>, commonFields: `[`CommonFields`](../-i-assertion-plant-with-common-fields/-common-fields/index.md)`<T?>)`

### Parameters

`description` - The description of this down-cast; will be used for the creation of the [IAssertion](../../ch.tutteli.atrium.assertions/-i-assertion/index.md).

`subClass` - The resulting type of the down-cast.

`commonFields` - The [IAssertionPlantWithCommonFields.CommonFields](../-i-assertion-plant-with-common-fields/-common-fields/index.md) of the
    [IAssertionPlant](../-i-assertion-plant/index.md)/[IAssertionPlantNullable](../-i-assertion-plant-nullable/index.md) which uses this [DownCastBuilder](index.md).
    The down-cast will be performed on its [subject](../-i-assertion-plant-with-common-fields/subject.md).
    Moreover, the containing information will inter alia be used in reporting.

**Constructor**

