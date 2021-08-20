package ru.netology.test;


import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;

public class CardTest {
    @Test
    void shouldTestApplicationGo() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Будникова Анастасия");
        $("[data-test-id=phone] input").setValue("+79170000000");
        $("[data-test-id=agreement]").click();
        $("[role=button]").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldTestInvalidName() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Анастасия1");
        $("[data-test-id=phone] input").setValue("+79170000000");
        $("[data-test-id=agreement]").click();
        $("[role=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldTestInvalidPhone() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Анастасия");
        $("[data-test-id=phone] input").setValue("9170000000");
        $("[data-test-id=agreement]").click();
        $("[role=button]").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldTestNameNotFilled() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79170000000");
        $("[data-test-id=agreement]").click();
        $("[role=button]").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldBe(visible).
                shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldTestNotAgreement() {
        open("http://localhost:9999");
        $("[data-test-id=name] input").setValue("Анастасия");
        $("[data-test-id=phone] input").setValue("+79170000000");
        $("[role=button]").click();
        $("[data-test-id=agreement].input_invalid .checkbox__text").shouldBe(visible).
                shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}

