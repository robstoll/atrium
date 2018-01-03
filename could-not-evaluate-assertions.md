
# Could not evaluate the defined assertion(s)
You come across this warning in two situations:
- if you have defined an assertion which implies narrowing the `subject`  and have defined subsequent assertions for the narrowed `subject`
- if you have defined an identification lambda on an empty iterable or one which only returns `null` and your identification lambda requires a present `subject`.

The first case applies to `isNotNull`, `isA` and `toThrow` assertions and the like 
where it might be that the subject is actually `null` is not of the expected type or no `Exception` was thrown at all.

The second case applies to `contains` assertions for `Iterable`.

For instance, if you define the following
```kotlin
assert(listOf<Int>()).contains { returnValueOf(subject::dec).toBe(2) }
```
then Atrium would like to state that you searched an entry whose `dec` function returns `2`. 
However, accessing `subject` in this place is not possible, because `subject` is not defined.
Or in other words, Atrium requires at least one entry in the list in order to be able to access `dec` through reflection.

Let's have a look at another example:
```kotlin
assert(listOf<Int>()).contains { toBe(2) }
```
In this case, Atrium is able to tell you, that you expected an entry which is `2` because we do not access `subject` directly within the identification lambda.

Should you get this warning even though you do not access `subject`, then you have most probably found a bug in Atrium.
Congratulations :wink: please [report the bug](https://github.com/robstoll/atrium/issues/new?title=could%20not%20evaluate%20the%20defined%20assertions:%20fun%20xyz),
we would be happy to know about it.

Do you have an idea how we could present an explanation even though a user accesses `subject`? 
That would be interesting as well. Please write the hint as an [issue on github](https://github.com/robstoll/atrium/issues/new?title=explanation%20with%20absent%20subject).