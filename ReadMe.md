# Дипломная работа “Облачное хранилище”

## Описание проекта
Разработанный ервис предоставляет с обой REST интерфейс для возможности загрузки файлов и вывода списка уже загруженных файлов пользователя.
Сервис обслуживает авторизованные запросы. Заранее подготовленное веб-приложение (FRONT) подключается к разработанному сервису без доработок, а также использовать функционал FRONT для авторизации, загрузки и вывода списка файлов пользователя.

## Функции сервиса
1. Вывод списка файлов
2. Добавление файла
3. Удаление файла
4. Авторизация

## Описание и запуск FRONT
1. Установить nodejs (версия не ниже 19.7.0) на компьютер следуя инструкции (https://nodejs.org/ru/download/).
2. Скачать FRONT(https://github.com/netology-code/jd-homeworks/blob/master/diploma/netology-diplom-frontend) (JavaScript)
3. Перейти в папку FRONT приложения и все команды для запуска выполнять из нее.
4. Следуя описанию README.md FRONT проекта запустить nodejs приложение (npm install, npm run serve).
5. Можно задать url для вызова своего backend сервиса:
    5.1. В файле `.env` FRONT (находится в корне проекта) приложения нужно изменить url до backend.
    5.2. Пересобрать и запустить FRONT снова: `npm run build`
    5.3. Измененный `url` сохранится для следующих запусков.

## Запуск клиентской и серверной частей:
1. FRONT запускается на порте 8081 и доступен по url в браузере http://localhost:8081
2. Для запуска приложения необходимо выполнить команду docker compose up.

## Варианты имени пользователя и пароля:
1. user / 123
2. user2 / 1234

