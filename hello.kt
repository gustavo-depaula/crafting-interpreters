import kotlin.io.print
import kotlin.io.readln
import kotlin.system.exitProcess
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;



fun main(args: Array<String>) {
    if (args.size > 1) {
        println("Usage: klox [script]")
        exitProcess(64)
    }

    if (args.size == 1) {
        runFile(args[0])
    } else {
        runPrompt()
    }
}

fun runFile(path: String) {
    val bytes = Files.readAllBytes(Paths.get(path))
    // run(String(bytes, Charset.defaultCharset()))
}

fun runPrompt() {
    while (true) {
        print("> ")
        val line = readln()
        if (line == null) break;
        // run(line)
    }
}
