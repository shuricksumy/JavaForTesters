package ru.stqa.pft.mantis.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ResetPasswordTests extends TestBase {

//TODO Реализовать сценарий смены пароля пользователю баг-трекера MantisBT администратором системы:
//
//Администратор входит в систему, переходит на страницу управления пользователями, выбирает заданного пользователя и нажимает кнопку Reset Password
//Отправляется письмо на адрес пользователя, тесты должны получить это письмо, извлечь из него ссылку для смены пароля, пройти по этой ссылке и изменить пароль.
//Затем тесты должны проверить, что пользователь может войти в систему с новым паролем.
//Изменить конфигурацию MantisBT можно вручную, не обязательно подменять конфигурационный файл при запуске тестов. Пользователя тоже можно заранее создать вручную.
//
//Однако получить информацию об идентификаторе и/или логине пользователя тесты должны самостоятельно во время выполнения. Можно это сделать, например, загрузив информацию о пользователях из базы данных.
//
//Почтовый сервер можно запускать непосредственно внутри тестов.
//
//Шаги 1 и 2 необходимо выполнять через пользовательский интерфейс, а шаг 3 можно выполнить на уровне протокола HTTP.

  @BeforeMethod
  public void checkUsersWithLocalEmail(){
    //Connect to DB and get list of user email
    //Parse to localhost
    //If null - create simple user
    if (app.db().simpleUsers().size() == 0) {
      System.out.println("NO ENABLED simpleUsers");
    }
  }

  @Test
  public void testResetPassword(){
    app.db().simpleUsers().stream().forEach((u) -> System.out.println("----------------" + u.toString()));
    //In DB find any user with local email
    //As admin reset password
    //Get an email
    //change password
    //use HTTP protocol and check login
  }



}
