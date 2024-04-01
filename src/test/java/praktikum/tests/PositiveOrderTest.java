package praktikum.tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import praktikum.pages.*;
import praktikum.support.DriverRule;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class PositiveOrderTest
{
    @Rule
    public DriverRule driverRule = new DriverRule();

    private final boolean topButton;

    private final String name;
    private final String surname;
    private final String addr;
    private final String station;
    private final String phone;
    private final String date;
    private final int days;
    private final String color;
    private final String comments;


    public PositiveOrderTest(boolean topButton,
                             String name,
                             String surname,
                             String addr,
                             String station,
                             String phone,
                             String date,
                             int days,
                             String color,
                             String comments)
    {
        this.topButton = topButton;
        this.name = name;
        this.surname = surname;
        this.addr = addr;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.days = days;
        this.color = color;
        this.comments = comments;
    }

    @Parameterized.Parameters
    public static Object [][]getTestData()
    {
        return new Object[][] {
                {
                        // нажать кнопку Заказать вверху страницы
                        true,

                        // клиент
                        "Юлия",
                        "Подгорная",
                        "г.Пенза, ул.Ладожская д.155, кв. 132",
                        "Лихоборы",
                        "+79063979676",

                        // детали
                        "05.04.2024",
                        1,
                        ScooterOrderDetailsPage.ColorId.BLACK,
                        ""
                },
                {
                        // нажать на кнопку Заказать в середине страницы
                        false,

                        // клиент
                        "Андрей",
                        "Подгорный",
                        "г.Москва, Варшавское шоссе, д.125",
                        "Южная",
                        "+79603213991",

                        // детали
                        "07.05.2024",
                        3,
                        ScooterOrderDetailsPage.ColorId.GREY,
                        "домофона нет"
                },
        };
    }

    @Test
    public void run()
    {
        WebDriver driver = driverRule.getDriver();

        ScooterMainPage main = new ScooterMainPage(driver);

        main.open();

        ScooterOrderCustomerPage orderCustomer;

        // клик на кнопку Заказать
        if (topButton)
            orderCustomer = main.clickOrderButtonTop();
        else
            orderCustomer = main.clickOrderButtonMiddle();

        // ожидание прогрузки первой формы оформления заказа
        orderCustomer.waitFor();

        // заполнение полей значениями параметров теста
        orderCustomer.setInputText(ScooterOrderCustomerPage.InputId.NAME, name);
        orderCustomer.setInputText(ScooterOrderCustomerPage.InputId.SURNAME, surname);
        orderCustomer.setInputText(ScooterOrderCustomerPage.InputId.ADDRESS, addr);
        orderCustomer.setInputText(ScooterOrderCustomerPage.InputId.STATION, station);
        orderCustomer.setInputText(ScooterOrderCustomerPage.InputId.PHONE, phone);

        ScooterOrderDetailsPage orderDetails = orderCustomer.clickNextButton();

        // ожидание прогрузки второй формы оформления заказа
        orderDetails.waitFor();

        // заполнение полей значениями параметров теста
        orderDetails.setDateText(date);
        orderDetails.selectDaysDropDown(days);
        orderDetails.clickColorCheckBox(color);
        orderDetails.setCommentText(comments);

        ScooterOrderConfirmPage orderConfirm = orderDetails.clickSubmitButton();

        // ожидание прогрузки формы подтверждения заказа
        orderConfirm.waitFor();

        // подтверждение заказа
        ScooterOrderDonePage done = orderConfirm.clickConfirmButton();

        done.waitFor();

        String orderText = done.getOrderText();

        // убедиться, что текст с номером заказа выдан пользователю
        // номер заказа как минимум 6 цифр
        assertTrue(orderText.length() >= 6);
    }
}
