package core;
//  Не доделанный фекалий
public class Kernel {
    public void boot() {
        System.out.println("⚙️ Ядро запущено.");
    }

    public void registerComponent(String name) {
        System.out.println("📦 Зарегистрирован компонент: " + name);
    }

    public void runComponent(String componentName) {
        try {
            String className = "containers." + componentName;
            Class<?> clazz = Class.forName(className);
            Object instance = clazz.getDeclaredConstructor().newInstance();
            clazz.getMethod("run").invoke(instance);
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Компонент не найден: " + componentName);
        } catch (Exception e) {
            System.out.println("❌ Ошибка при запуске компонента: " + e.getMessage());
        }
    }
}
