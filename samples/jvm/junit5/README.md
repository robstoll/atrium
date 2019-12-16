# Sample JS project: Atrium with Junit5

This example project is based on [kotlin's example](https://github.com/Kotlin/kotlin-examples/tree/master/gradle/hello-world).
Yet with a few optimizations and adaptations:
- it uses `--prefer-offline` for a faster build and offline capability
- it uses sourceMap so that debugging in kotlin code is possible (as good as your IDE supports it)
- it includes all test dependencies, also runtime only dependencies
- it does not force a `moduleKind` on src/main
- last but not least and this is the important part, **it establishes a dependency to Atrium on JVM level** 
  which is necessary due to the loosely coupled design of Atrium and dead code elimination.

This sample project defines a dependency on the bundle module `atrium-cc-en_GB`.
It does so by using a project dependency (this way CI builds it as well and we can be sure that we provide you a working example).
   
