#language: ru
@all
  Функция: Регресс

    Сценарий: Создание нового пользователя
      Допустим получить body запроса
      И отправить POST запрос на создание нового пользователя
      И сверяем имя пользователя из запроса с именем пользователя из ответа
      И сверяем работу пользователя из запроса с работой пользователя из ответа
