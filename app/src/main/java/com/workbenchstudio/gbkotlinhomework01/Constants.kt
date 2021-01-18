package com.workbenchstudio.gbkotlinhomework01

import com.workbenchstudio.gbkotlinhomework01.entity.Question

object Constants {

    const val USER_NAME: String = "user_name"
    const val TOTAL_QUESTIONS: String = "total_question"
    const val CORRECT_ANSWERS: String = "correct_answers"
    const val ANSWER_NOT_CHOOSE: Int = 404

    fun getQuestions() : ArrayList<Question> {
        val questionsList = ArrayList<Question>()

        val question1 = Question(1, "Что является принципом ООП?", "Нивелирование", "Обслуживание",
                "Наливание", "Наследование", 4);
        questionsList.add(question1)

        val question2 = Question(2, "Что из нижеперечисленного является модификатором доступа?", "Для общего пользования", "Совершенно секретно",
                "Приватный", "Запрещено к печати в СМИ", 3);
        questionsList.add(question2)

        val question3 = Question(3, "Что такое наследование?", "Возможность приобретать свойства другого объекта", "Явление культуры, быта и т. п., полученное от предыдущих эпох, от прежних деятелей",
        "Имущество, переходящее после смерти его владельца к новому лицу.", "Использование одного имени для задания общих для класса действий", 1);
        questionsList.add(question3)

        val question4 = Question(4, "Что такое JDK?", "Реализация виртуальной машины",
                "Комплект разработки на языке Java", "Виртуальная машина, часть исполняющей системы", "Just Don't Know", 2);
        questionsList.add(question4)

        val question5 = Question(5, "Какой доступ по умолчанию в Java? (default)", "По умолчанию закрыт для всех",
                "По умолчанию закрыт для всех", "Доступ на уровне пакета",  "Доступ на уровне иерархии", 3);
        questionsList.add(question5)
        return questionsList
    }
}