package api.data_providers;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class AppealData {

    static Stream<Arguments> provideDifferentFilterBodies() {
        return Stream.of(
                Arguments.of(
                        "Без фильтрации",
                        "{}"
                ),
                Arguments.of(
                        "Фильтрация по objectKind",
                        "{\"objectKind\":[\"57e9a44c-f6f3-4350-a8fe-bc37d9e902f8\"]}"
                ),
                Arguments.of(
                        "Фильтрация по диапазону дат",
                        "{\"dateFrom\":\"2026-04-30T21:00:00Z\",\"dateTo\":\"2026-05-20T20:59:59.999Z\"}"
                ),
                Arguments.of(
                        "Фильтрация по Объект ФНО",
                        "{\"objectFunctionalPurpose\":[\"bad5234e-e94b-41f3-bb33-06e7150e7985\"]}"
                ),
                Arguments.of(
                        "Фильтрация по заявителю",
                        "{\"applicant\":[\"d03da375-97f4-4502-a03e-8a798483f16b\"]}"
                )
        );
    }
}
