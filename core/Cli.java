package core;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Cli {
    public static void makeComponent(String name) {
        String className = Character.toUpperCase(name.charAt(0)) + name.substring(1);
        String filePath = "containers/" + className + ".java";
        String content = "package containers;\n\npublic class " + className + " {\n    public void run() {\n        System.out.println(\"Компонент " + className + " работает\");\n    }\n}\n";
        try {
            Files.createDirectories(Paths.get("containers"));
            FileWriter writer = new FileWriter(filePath);
            writer.write(content);
            writer.close();
            System.out.println("✅ Компонент создан: " + filePath);
        } catch (IOException e) {
            System.out.println("❌ Ошибка при создании компонента: " + e.getMessage());
        }
    }

    public static void buildExe() {
        try {
            System.out.println("🏗️ Сборка jar...");

            // 1. Manifest
            Files.write(Paths.get("manifest.txt"), "Main-Class: Init\n".getBytes());

            // 2. Компиляция
            Process p1 = new ProcessBuilder("javac", "-encoding", "UTF-8", "Init.java", "core/*.java", "containers/*.java").inheritIO().start();

            p1.waitFor();

            // 3. JAR ГАВНОО ТУТ
            Process p2 = new ProcessBuilder("jar", "cfm", "app.jar", "manifest.txt",
                    "Init.class", "core/Kernel.class", "core/Cli.class").inheritIO().start();
            p2.waitFor();

            System.out.println("✅ JAR собран: app.jar");

            // 4. jpackage → EXE ГАВНОО И ТУТ
            Process p3 = new ProcessBuilder(
                "jpackage",
                "--input", ".",
                "--name", "Init",
                "--main-jar", "app.jar",
                "--main-class", "Init",
                "--type", "app-image" 
            ).inheritIO().start();

            p3.waitFor();

            System.out.println("🎉 EXE создан: Init.exe");

        } catch (Exception e) {
            System.out.println("❌ Ошибка сборки EXE: " + e.getMessage());
        }
    }
}
