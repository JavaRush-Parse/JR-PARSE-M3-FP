package com.java.classes;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuestRepositoryTest {
    private final QuestRepository questRepository = QuestRepository.getInstance();
    @Test
    @DisplayName("getInstance() має повертати один і той самий екземпляр (Singleton)")
    void getInstance_shouldReturnSameInstance() {
        QuestRepository anotherInstance = QuestRepository.getInstance();
        assertSame(questRepository, anotherInstance);
    }

    @Test
    @DisplayName("getNode() має повертати правильні тексти запитань для існуючих ID")
    void getNode_shouldReturnCorrectQuestionText() {
        assertAll("Порівняння з разними id",
                () -> assertEquals("Ви втрачаєте пам'ять", questRepository.getNode(0).getQuestionText()),
                () -> assertEquals("Ви прийняли виклик.Піднятися на капітанський місток?", questRepository.getNode(1).getQuestionText()),
                () -> assertEquals("Ви відхилили виклик. Поразка", questRepository.getNode(2).getQuestionText()),
                () -> assertEquals("Ви піднялися на місток. Хто ви?", questRepository.getNode(3).getQuestionText()),
                () -> assertEquals("Ви не пішли на переговор. Поразка", questRepository.getNode(4).getQuestionText()),
                () -> assertEquals("Вас повернули додому. Перемога", questRepository.getNode(5).getQuestionText()),
                () -> assertEquals("Ваша брехня була викрита. Поразка", questRepository.getNode(6).getQuestionText())
        );
    }

    @Test
    @DisplayName("Має правильно визначати програшні та не програшні вузли")
    void shouldCorrectlyIdentifyLosingNodes() {
        assertAll("Перевірка статусу True or False",
                () -> assertFalse(questRepository.getNode(0).isLosingNode()),
                () -> assertFalse(questRepository.getNode(1).isLosingNode()),
                () -> assertTrue(questRepository.getNode(2).isLosingNode()),
                () -> assertFalse(questRepository.getNode(3).isLosingNode()),
                () -> assertTrue(questRepository.getNode(4).isLosingNode()),
                () -> assertTrue(questRepository.getNode(5).isLosingNode()),
                () -> assertTrue(questRepository.getNode(6).isLosingNode())
        );
    }

    @Test
    @DisplayName("Nodes мають відповіді для продовження гри з правильними відповідями")
    void terminalNodes_thatHaveSomeChoices() {
        Map<Integer, String> expectedChoicesForNode0 = Map.of(
                1, "Прийняти виклик",
                2, "Відхилити виклик"
        );
        Map<Integer, String> expectedChoicesForNode1 = Map.of(
                3, "Піднятися на місток",
                4, "Відмовитися підніматися на місток"
        );

        Map<Integer, String> expectedChoicesForNode3 = Map.of(
                5, "Розповісти правду про себе",
                6, "Збрехати про себе"
        );

        assertAll("Перевірка мап з варіантами відповідей",
                () -> assertEquals(expectedChoicesForNode0, questRepository.getNode(0).getChoices()),
                () -> assertEquals(expectedChoicesForNode1, questRepository.getNode(1).getChoices()),
                () -> assertEquals(expectedChoicesForNode3, questRepository.getNode(3).getChoices())
        );
    }

    @Test
    void terminalNodes_thatHaveEmptyChoices(){
        assertAll("Перевірка порожніх відповідей у кінцевих вузлах",
                () -> assertEquals(new HashMap<>(),questRepository.getNode(2).getChoices()),
                () -> assertEquals(new HashMap<>(),questRepository.getNode(4).getChoices()),
                () -> assertEquals(new HashMap<>(),questRepository.getNode(5).getChoices()),
                () -> assertEquals(new HashMap<>(),questRepository.getNode(6).getChoices())

        );
    }
}