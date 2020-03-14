# Sample JS project: Atrium with mocha

This example project is heavily based on [kotlin's example](https://github.com/JetBrains/kotlin-examples/blob/master/gradle/js-tests/mocha).
Yet with a few optimizations and adaptations:
- it stores nodejs and npm only once per project (and not for every sub project)
- it uses `--prefer-offline` for a faster build and offline capability
- it uses sourceMap so that debugging in kotlin code is possible (as good as your IDE supports it)
- it includes all test dependencies, also runtime only dependencies
- it does not force a `moduleKind` on src/main
- last but not least and this is the important part, **it establishes a dependency to Atrium on JS level** 
  which is necessary due to the loosely coupled design of Atrium and dead code elimination.


 
This sample project defines a dependency on the bundle module `atrium-fluent-en_GB-robstoll-js`.
It does so by using a project dependency (this way CI builds it as well and we can be sure that we provide you a working example).
Therefore you need to delete line 28,29 in [build.gradle](https://github.com/robstoll/atrium/tree/master/samples/js/mocha/build.gradle#L28)
and uncomment line 30.

Change line 4 in [build.gradle](https://github.com/robstoll/atrium/tree/master/samples/js/mocha/build.gradle#L4)
to `infix-en_GB-robstoll` in case you want to use the infix API.   
