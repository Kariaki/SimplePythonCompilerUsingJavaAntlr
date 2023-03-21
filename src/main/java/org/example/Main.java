package org.example;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.example.antlr4.*;
import org.example.gen.ImprovedGrammarBaseListener;
import org.example.gen.ImprovedGrammarLexer;
import org.example.gen.ImprovedGrammarParser;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        try {
            String content = Files.readString(Paths.get("input.txt"), StandardCharsets.UTF_8);


            String line = content;
            System.out.println(line);

            // Process the string
            System.out.println("String read from file: " + line);

            CharStream input = CharStreams.fromString(line);

            // Create a lexer that reads from the CharStream
            ImprovedGrammarLexer lexer = new ImprovedGrammarLexer(input);

            // Create a buffer of tokens pulled from the lexer
            CommonTokenStream tokens = new CommonTokenStream(lexer);

            // Create a parser that reads from the token buffer
            ImprovedGrammarParser parser = new ImprovedGrammarParser(tokens);

            // Parse the program rule
            ParseTree tree = parser.program();

            // Walk the parse tree with a custom listener that generates Python code
            ParseTreeWalker walker = new ParseTreeWalker();
            ImprovedGrammarPythonListener listener = new ImprovedGrammarPythonListener();

            walker.walk(listener, tree);
            String code = listener.getPythonCode();
            System.out.println(code);

            FileWriter writer = new FileWriter("output.py");
            writer.write(code);
            writer.close();
            System.out.println("Successfully wrote Python code to output.py");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());

        }
    }}