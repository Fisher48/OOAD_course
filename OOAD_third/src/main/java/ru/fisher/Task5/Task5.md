Задание 5. Выделите основные классы проектирования (АТД) в проекте - только на уровне их названий (на английском) и краткого описания в несколько слов (на русском).

Решение 5-го задания:

Основные классы проектирования:

* GameCommand - класс построенный по паттерну Команда, напрашивается логика управления объектами игры (взаимодействие) со стороны Пользователя.

* GameObserver - класс построенный по паттерну Наблюдатель, будет оповещать систему как действовать при Запущенной игре, Завершенной или Перезапуске.

* GameFacade - класс будет построен по паттерну Фасад, и будет скрывать сложную логику от Пользователя и упростит интерфейс управления игрой.

* ElementsFabric - класс будет построен по паттерну Фабрика или простой Фабрика, для генерации элементов в зависимости от типа.