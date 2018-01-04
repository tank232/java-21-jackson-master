package com.tel.ran;

import com.tel.ran.annotations.InjectionService;
import com.tel.ran.annotations.InjectionServiceType;
import com.tel.ran.core.Creature;
import com.tel.ran.core.Operations;
import com.tel.ran.pojo.Car;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.reflections.Reflections;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class App {

    public static void main(String[] args) throws Exception {
        // printCar();
        createCars();
    }

    private static void createCars() throws Exception {
        File directory = new File(Paths.get("").toAbsolutePath().toString() + File.separator + "files");
        File[] files = directory.listFiles();
        InjectionServiceType CreateType;
        for (File file : files) {
            if (file.isFile()) {
                String extension = FilenameUtils.getExtension(file.toString()).toUpperCase();
                switch (extension) {
                    case "JSON":
                        CreateType = InjectionServiceType.JSON;
                        break;
                    case "XML":
                        CreateType = InjectionServiceType.XML;
                        break;
                    default:
                        continue;
                }
                String fileContent = readFile(file);
                if (fileContent.length() != 0) {
                    Set<Class<?>> setClass = getClass(InjectionService.class, Creature.class, CreateType);
                    for (Class<?> clazz : setClass) {
                        Creature creature = (Creature) clazz.newInstance();
                        creature.create(fileContent);
                    }
                }
            }
            //  file.delete();
        }
        System.out.println(Car.getCars());
    }

    private static String readFile(File file) throws IOException {
        List<String> strings = FileUtils.readLines(file, "UTF-8");
        StringBuilder builder = new StringBuilder();
        strings.forEach(builder::append);
        return builder.toString();
    }

    private static void printCar() throws Exception {
        Car car = new Car(
                "Toyota",
                "Corolla",
                2006,
                200000);

        Set<Class<?>> setClass = getClass(InjectionService.class, Operations.class, InjectionServiceType.XML);
        for (Class<?> clazz : setClass) {
            Operations operations = (Operations) clazz.newInstance();
            operations.print(car);
        }
    }

    private static Set<Class<?>> getClass(Class<? extends Annotation> classAnotation, Class<?> classImpliment, InjectionServiceType type) {
        Reflections reflections = new Reflections("com.tel.ran.core");
        Set<Class<?>> setClass = new HashSet<>();
        for (Class<?> clazz : reflections.getTypesAnnotatedWith(classAnotation)) {

            if (checkImplement(clazz, classImpliment)) {

                if (clazz.getAnnotation(InjectionService.class).type() == type) {
                    setClass.add(clazz);
                }
            }
        }
        return setClass;
    }

    private static boolean checkImplement(Class<?> clazz, Class<?> classImpliment) {
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> inter : interfaces) {
            if (inter.equals(classImpliment))
                return true;
        }
        return false;
    }
}
