plugins {
    id("java")
    id("build-logic.gradle-conventions")
}

java {
    toolchain {
        // reading JAVA_VERSION from env to enable jdk17 build in CI
        languageVersion.set(JavaLanguageVersion.of(buildParameters.java.version))
    }
    consistentResolution {
        useCompileClasspathVersions()
    }
}


tasks.configureEach<JavaCompile> {
    inputs.property("java.version", System.getProperty("java.version"))
    inputs.property("java.vm.version", System.getProperty("java.vm.version"))
    sourceCompatibility = buildParameters.defaultJdkVersion.toString()
    targetCompatibility = buildParameters.defaultJdkVersion.toString()
    options.apply {
        encoding = "UTF-8"
        compilerArgs.add("-Xlint:deprecation")
        if (buildParameters.java.werror) {
            compilerArgs.add("-Werror")
        }
    }
}
