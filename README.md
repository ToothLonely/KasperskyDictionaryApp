# Приложение рецептов

Это приложение - русско-английский словарь, в котором пользователь вводит слово на английском языке и получает перевод слова на русском языке. 
В приложении автоматически сохраняется история переводов, так же можно вручную сохранять перевод в избранное. Помимо этого можно удалять слова из истории и списка избранных.  
Приложение написано в соответствии с рекомендациями Google, по принципам Clean Architecture.

### Автор

**ToothLonely**

# Стек:

| Категория     | Технология                                                          |
|---------------|---------------------------------------------------------------------|
| Architecture  | ![MVVM](https://img.shields.io/badge/MVVM-purple)                   |
| Language      | ![Kotlin](https://img.shields.io/badge/Kotlin-blue)                 |
| UI            | ![Views](https://img.shields.io/badge/Views-XML-lightgrey)          |
| Network       | ![Retrofit](https://img.shields.io/badge/Retrofit-green)            |
| Database      | ![Room](https://img.shields.io/badge/Room-red)                      |
| DI            | ![Hilt](https://img.shields.io/badge/Hilt-orange)                   |
| Navigation    | ![Navigation](https://img.shields.io/badge/Navigation-Jetpack-grey) |
| Concurrency   | ![Coroutines](https://img.shields.io/badge/Coroutines-yellowgreen)  |

# Структура проекта:

| Package | Content                                                     |
|---------|-------------------------------------------------------------|
| app/    | Application                                                 | 
| data/   | Database, API service, DAOs, DI Module, Repository          | 
| model/  | Модели данных (data classes) для использования в приложении | 
| ui/     | Fragments, ViewModels, Adapters, MainActivity               |
