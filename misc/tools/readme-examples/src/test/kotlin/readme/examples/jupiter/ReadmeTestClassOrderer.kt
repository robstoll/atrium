package readme.examples.jupiter

import org.junit.jupiter.api.ClassDescriptor
import org.junit.jupiter.api.ClassOrderer
import org.junit.jupiter.api.ClassOrdererContext


class ReadmeTestClassOrderer : ClassOrderer {
    override fun orderClasses(context: ClassOrdererContext) {
        val index = context.classDescriptors.indexOfFirst { it.testClass == WriteReadmeTest::class.java }
        if (index >= 0) {
            val descriptor = context.classDescriptors.removeAt(index)
            @Suppress("UNCHECKED_CAST")
            (context.classDescriptors as MutableList<ClassDescriptor>).add(descriptor)
        }
    }
}
