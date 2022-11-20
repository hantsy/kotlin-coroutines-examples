package com.example.demo

import jdk.jpackage.internal.WindowsAppImageBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.file.Files
import java.nio.file.Path

suspend fun main(args: Array<String>) {
    withContext(Dispatchers.IO) {
        val path = Path.of("src/main/resources/test.csv")
        println("path: ${path.toAbsolutePath()}")
        Files.readAllLines(path)
            .stream()
            .skip(1)
            .forEach {
                println("reading line: $it")
            }
    }
}