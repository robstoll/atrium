package readme.examples.jupiter

import org.junit.jupiter.api.extension.ExtendWith

/**
 * The tests and error message are written here and automatically placed into the README via generation.
 * The generation is done during the project built. To trigger it manually, you have to run:
 * ```
 * ./gradlew :readme-examples:build
 * ```
 *
 * There are currently three kind of tags supported:
 * - <ex-xy> => places code and output into the tag
 * - <exs-xy> => places code into the tag, output will be put into a tag named <exs-xy-output>
 * - <code> => is not supposed to fail and only the code is put into the code
 *
 * Moreover, all tags can reuse snippets defined in this file with corresponding markers
 * //snippet-xy-start
 * ...
 * //snippet-xy-end
 *
 * and then in code
 * ```
 * fun `ex-...`(){
 *   //snippet-xy-insert
 * }
 */
@ExtendWith(ReadmeInvocationInterceptor::class)
interface ReadmeTest
