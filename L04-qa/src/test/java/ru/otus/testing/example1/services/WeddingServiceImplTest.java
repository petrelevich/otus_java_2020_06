package ru.otus.testing.example1.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.testing.example1.model.Family;
import ru.otus.testing.example1.model.Human;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@DisplayName("Сервис должен")
class WeddingServiceImplTest {

    @DisplayName("Возвращать семью из мужчины и женщины (используется mock)")
    @Test
    void shouldReturnFamilyWithMaleHusbandAndFemaleWife_MockUsed() {
        HumanGenerator humanGenerator = mock(HumanGenerator.class);
        WeddingService weddingService = new WeddingServiceImpl(humanGenerator);

        given(humanGenerator.generate('м')).willReturn(new Human('м'));
        given(humanGenerator.generate('ж')).willReturn(new Human('ж'));

        Family family = weddingService.doWedding();
        assertThat(family.getHusband().getSex()).isEqualTo('м');
        assertThat(family.getWife().getSex()).isEqualTo('ж');

        verify(humanGenerator, times(1)).generate('м');
        verify(humanGenerator, times(1)).generate('ж');
    }

    @DisplayName("Возвращать семью из мужчины и женщины (используется spy)")
    @Test
    void shouldReturnFamilyWithMaleHusbandAndFemaleWife_SpyUsed() {
        HumanGenerator humanGenerator = Mockito.spy(new HumanGeneratorImpl());
        WeddingService weddingService = new WeddingServiceImpl(humanGenerator);

        Family family = weddingService.doWedding();
        assertThat(family.getHusband().getSex()).isEqualTo('м');
        assertThat(family.getWife().getSex()).isEqualTo('ж');

        verify(humanGenerator, times(1)).generate('м');
        verify(humanGenerator, times(1)).generate('ж');
    }
}