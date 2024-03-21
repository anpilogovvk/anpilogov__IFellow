Название проекта: Автотесты веб-сервиса Jira

Описание:

Этот репозиторий содержит набор автоматизированных тестов пользовательского интерфейса (UI) для веб-сервиса Jira. Эти
тесты предназначены для проверки функциональности, производительности и удобства использования веб-приложения Jira.

Установка:

Установите необходимые зависимости:
Java 11 Maven Selenium WebDriver JUnit 5 Клонируйте этот репозиторий:
git clone https://github.com/anpilogovvk/anpilogov__IFellow.git
Перейдите в каталог проекта:
cd anpilogov_IFellow

Установите зависимости Maven:
mvn install

Запуск тестов:

Убедитесь, что веб-сервис Jira запущен и доступен. Запустите тесты с помощью Maven:
mvn test

Структура проекта:

src/main/java: Содержит степы src/test/test: Содержит вебхуки, тесты и файл properties

Типы тестов:

Этот набор тестов включает различные типы тестов, в том числе:

Функциональные тесты: Проверяют правильность работы основных функций Jira. Тесты удобства использования: Оценивают
удобство использования и соответствие стандартам доступности. UI тесты: Проверяют правильность работы основных функций
на фронте

Отчетность:

Результаты тестов отображаются в консоли или Allure.
