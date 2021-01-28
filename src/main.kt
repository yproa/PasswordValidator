

fun commands(){
    println("\nСписок команд:")
    println("L - сменить флаг у проверки на длинну")
    println("N - сменить флаг у проверки на наличия цифр")
    println("C - сменить флаг у проверки на разные регистры")
    println("S - сменить флаг у проверки на специальный символ")
    println("E - сменить флаг у проверки на энтропию")
    println("R - выводит текущие значения правил")
    println("HELP - для помощи")
    println("P - начать ввод пароль для проверки")
}



fun main() {
    var rules: MutableMap<String, Boolean> =
        mutableMapOf("length" to true, "number" to true, "case" to true, "symbol" to true, "entropy" to true)
    println("Добро пожаловать в валидатор паролей, сейчас установлены такие правила:")
    for ((key, value) in rules) {
        println("Правило $key, имеет значение $value")
    }
    commands()


    while (true) {
        println("Ожидаем ввода команды..")
        val inp = readLine().toString().toLowerCase()


        val _inp = when (inp) {//определяем что ввёл юзер
            "l" -> "length"
            "n" -> "number"
            "c" -> "case"
            "s" -> "symbol"
            "e" -> "entropy"
            "p" -> "p"
            "r" -> "r"
            "help" -> "h"
            else -> "err"
        }

        if (_inp=="err"){
            println("Вы что-то напутали!")
        }
        else if (_inp=="p"){//тут будут проверки и вывод ответа
            val password = readLine().toString();
            val validator = Validator(password,rules);
            val errors = validator.validate()
            if (errors.isEmpty())
                println("All done");
            else {
                for ((e,z) in errors)
                    println(z);
            }
        }
        else if (_inp=="r"){//выводим правила
            for ((key, value) in rules) {
                println("Правило $key, имеет значение $value")
            }
        }
        else if (_inp=="h"){//выводим команды
            commands()
        }
        else{//тут мы меняем флаг
            val rl: Boolean =rules.getValue(_inp);
            rules[_inp]=!rl;//закостылил
            println("\nПравило $_inp, изменено на  "+!rl);
        }




    }
}