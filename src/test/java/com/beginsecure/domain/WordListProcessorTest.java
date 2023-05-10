package com.beginsecure.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class WordListProcessorTest {

    // Створення мок-об'єкту для класу UserInput
    @Mock
    private UserInput userInputMock;

    // Створення об'єкту, який буде тестуватися
    @InjectMocks
    private WordListProcessor wordListProcessor;

    // Тест для перевірки успішного виконання методу processList
    @Test
    public void testProcessList_Success() {
        String input = "apple, orange, egg, grapefruit";
        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("egg");
        expectedOutput.add("apple");
        expectedOutput.add("orange");
        expectedOutput.add("grapefruit");

        // Підставляємо певне значення для мок-об'єкту
        Mockito.when(userInputMock.getInput()).thenReturn(input);

        List<String> actualOutput = wordListProcessor.processList(userInputMock.getInput());

        // Перевірка очікуваного результату з отриманим
        Assertions.assertEquals(expectedOutput, actualOutput);
        // Перевірка того, що метод getInput був викликаний рівно один раз
        Mockito.verify(userInputMock, Mockito.times(1)).getInput();
    }

    // Тест для перевірки обробки довгих слів
    @Test
    public void testProcessList_LongWords() {
        String input = "antidisestablishmentariani, pneumonoultramicroscopicsilicovolcanoconiosis, hippopotomonstrosesquippedaliophobia";
        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("antidisestablishmentariani");
        expectedOutput.add("pneumonoultramicroscopicsilico");
        expectedOutput.add("hippopotomonstrosesquippedalio");

        Mockito.when(userInputMock.getInput()).thenReturn(input);

        List<String> actualOutput = wordListProcessor.processList(userInputMock.getInput());

        Assertions.assertEquals(expectedOutput, actualOutput);
        Mockito.verify(userInputMock, Mockito.times(1)).getInput();
    }

    // Тест для перевірки обробки порожнього вводу
    @Test
    public void testProcessList_EmptyInput() {
        String input = "";
        List<String> expectedOutput = new ArrayList<>();

        Mockito.when(userInputMock.getInput()).thenReturn(input);

        List<String> actualOutput = wordListProcessor.processList(userInputMock.getInput());

        Assertions.assertEquals(expectedOutput, actualOutput);
        Mockito.verify(userInputMock, Mockito.times(1)).getInput();
    }


    // Тест за якого мок об’єкт генерує виключення
    @Test
    public void testProcessList_Exception() {
        String input = "apple, banana, orange, grapefruit";

        // Підстановка винятку, який буде генеруватися при виклику методу getInput
        Mockito.when(userInputMock.getInput()).thenThrow(new RuntimeException("Exception during input"));

        // Перевірка того, що метод processList викликає виняток RuntimeException
        Assertions.assertThrows(RuntimeException.class, () -> {
            wordListProcessor.processList(userInputMock.getInput());
        });

        // Перевірка того, що метод getInput був викликаний рівно один раз
        Mockito.verify(userInputMock, Mockito.times(1)).getInput();
    }


    // перевіряє обробку дублікатів слів вхідного рядка.
    @Test
    public void testProcessList_DuplicateWords() {
        String input = "horse, cat, giraffe, horse, elephant, giraffe";
        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("cat");
        expectedOutput.add("horse");
        expectedOutput.add("giraffe");
        expectedOutput.add("elephant");

        Mockito.when(userInputMock.getInput()).thenReturn(input);

        List<String> actualOutput = wordListProcessor.processList(userInputMock.getInput());

        Assertions.assertEquals(expectedOutput, actualOutput);
        Mockito.verify(userInputMock, Mockito.times(1)).getInput();
    }


    // Тест для перевірки часткового моку об'єкта
    @Test
    public void testProcessList_PartialMock() {
        String input = "apple, banana, orange, grapefruit";
        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add("apple");
        expectedOutput.add("banana");
        expectedOutput.add("orange");
        expectedOutput.add("grapefruit");

        // Створення часткового мок-об'єкту
        WordListProcessor spyWordListProcessor = Mockito.spy(new WordListProcessor());

        // Підстановка реального методу для методу processList
        Mockito.when(spyWordListProcessor.processList(Mockito.anyString())).thenCallRealMethod();
        Mockito.when(userInputMock.getInput()).thenReturn(input);

        List<String> actualOutput = spyWordListProcessor.processList(userInputMock.getInput());

        Assertions.assertEquals(expectedOutput, actualOutput);
        Mockito.verify(userInputMock, Mockito.times(1)).getInput();
        // Перевірка того, що метод processList був викликаний рівно один раз
        Mockito.verify(spyWordListProcessor, Mockito.times(1)).processList(Mockito.anyString());
    }
}