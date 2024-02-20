import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@ExtendWith(RunTest.CustomTestWatcher.class)
public class RunTest {

    @BeforeAll
    public static void dateInfo() {
        String formattedDate = generateFormattedDate();
        System.out.println(formattedDate);
    }

    @Test
    public void checkModeСaseInLineFalse() {
        Assertions.assertTrue(checkIfWordsAreCapitalized("John doe"));
        Assertions.assertTrue(checkIfWordsAreCapitalized("иван иванович"));
    }

    @Test
    public void checkModeСaseInLineTrue() {
        Assertions.assertTrue(checkIfWordsAreCapitalized("John Doe"));
        Assertions.assertTrue(checkIfWordsAreCapitalized("Иван Иванович"));
    }

    @Test
    public void testObjectFieldsNotEmpty() {
        Object object = new Object() {
            public String field1 = "value1";
            public String field2 = "value2";
            public String field3 = "value3";
        };
        boolean result = areAllFieldsNotEmpty(object);
        Assertions.assertTrue(result);
    }

    @Test
    public void testObjectFieldsEmpty() {
        Object object = new Object() {
            public String field1 = "value1";
            public String field2 = "value2";
            public String field3 = null;
        };
        boolean result = areAllFieldsNotEmpty(object);
        Assertions.assertTrue(result);
    }

    public static class CustomTestWatcher implements TestWatcher {
        @Override
        public void testSuccessful(ExtensionContext context) {
            System.out.println("Test was successful!");
        }

        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            System.out.println("Test was failed!");
        }
    }

    public static boolean checkIfWordsAreCapitalized(String sentence) {
        // Разделяем строку на слова по пробелу или знаку препинания
        String[] words = sentence.split("[\\s\\p{Punct}]+");
        // Проверяем каждое слово в массиве
        for (String word : words) {
            // Если первая буква слова не является заглавной, возвращаем false
            if (!Character.isUpperCase(word.charAt(0))) {
                return false;
            }
        }
        // Если все слова проходят проверку, возвращаем true
        return true;
    }

    //метод проверяет, что в объекте все поля не null
    public static boolean areAllFieldsNotEmpty(Object object) {
        List<Field> fields = Arrays.asList(object.getClass().getDeclaredFields());
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(object) == null) {
                    return false;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static String generateFormattedDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("'Тесты запущены в' HH:mm dd MMMM yyyy 'года'", Locale.forLanguageTag("ru-RU"));
        return now.format(formatter);
    }

}



