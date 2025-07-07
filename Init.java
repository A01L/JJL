import core.Kernel;
import core.Cli;

public class Init {
    public static void main(String[] args) {
        Kernel kernel = new Kernel();
        kernel.boot();

        if (args.length > 0) {
            switch (args[0]) {
                case "make":
                    if (args.length > 1) {
                        Cli.makeComponent(args[1]);
                    } else {
                        System.out.println("⚠️ Укажите имя компонента: java Init make MyComponent");
                    }
                    break;

                case "run":
                    // Пиздец полная тут (заменить что бы через Kernel регистрировать компонент тут и там как в PYJL)
                    if (args.length > 1) {
                        kernel.runComponent(args[1]);
                    } else {
                        System.out.println("⚠️ Укажите имя компонента: java Init run MyComponent");
                    }
                    break;

                case "build":
                    // Хуйня как-то
                    Cli.buildExe(); 
                    break;

                default:
                    System.out.println("❌ Неизвестная команда: " + args[0]);
            }
        } else {
            System.out.println("ℹ️ Используйте команды: make, run, build");
        }
    }
}
