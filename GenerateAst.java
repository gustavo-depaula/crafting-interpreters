package me.dpgu.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class GenerateAst {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: generate_ast <output directory>");
            System.exit(64);
        }
        String outputDir = args[0];
        defineAst(
                  outputDir, "Expr",
                  Arrays.asList("Binary   : Expr left, Token operator, Expr right",
                                "Grouping : Expr expression",
                                "Literal  : Object value",
                                "Unary    : Token operator, Expr right"));
    }

    public static void defineAst(String outputDir,
                                 String baseName,
                                 List<String> types)
        throws IOException {
        String path = outputDir + "/" + baseName + ".java";
        PrintWriter writer = new PrintWriter(path, "UTF-8");

        writeToFile(writer,
                    Arrays.asList("package me.dpgu.lox;",
                                  "",
                                  "import java.util.List;",
                                  "",
                                  "abstract class " + baseName + " {"));

        for (String type : types) {
            String className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();
            defineType(writer, baseName, className, fields);
        }

        writer.println("}");
        writer.close();
    }

    private static void defineType(PrintWriter writer, String baseName,
                                   String className, String fieldList)
        throws IOException {
        List<String> lines = new ArrayList<String>
            (Arrays.asList("  static class " + className + " extends " + baseName + " {",
                                           "    " + className + "(" + fieldList + ") {"));

        // extract fields, constructor
        String[] fields = fieldList.split(", ");
        for (String field : fields) {
            String name = field.split(" ")[1];
            lines.add("      this." + name + " = " + name + ";");
        }

        lines.add("    }");
        lines.add("");


        // final fields
        for (String field : fields) {
            lines.add("    final " + field + ";");
        }
        lines.add("  }");
        lines.add("");

        writeToFile(writer, lines);
    }

    public static void writeToFile(PrintWriter writer, List<String> lines)
        throws IOException {
        lines.forEach(line -> writer.println(line));
    }
}
