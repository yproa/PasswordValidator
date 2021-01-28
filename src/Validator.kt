class Validator(val password: String,val rulesLst:MutableMap<String, Boolean>) : Rules {

    fun validate(): MutableMap<String, String> {
        var errors: MutableMap<String, String> = mutableMapOf()

        if ((rulesLst.getValue("length"))&&(this.lengthRule(password))) errors["lenght"] = "Длинна должна составлять 8-20 символов"
        if ((rulesLst.getValue("number"))&&(this.numberRule(password))) errors["number"] = "Нужна как минимум 1 цифра"
        if ((rulesLst.getValue("case"))&&(this.lowerCaseRule(password) || this.upperCaseRule(password))) errors["case"] = "Должен быть как минимуи 1 символ нижнего и верхнего регистра"
        if ((rulesLst.getValue("symbol"))&&(this.specialSymbolsRule(password))) errors["symbol"] = "Нужнен как минимум 1 спец. символ"
        if ((rulesLst.getValue("entropy"))&&(this.entropyRule(password))) errors["entropy"] = "Ваш пароль слишком простой!"

        var valuessum:Boolean=false;
        for ((e,z) in rulesLst)
            valuessum=valuessum || z;
        if (valuessum==false){
            errors["rules"] = "У вас не задано правил"
        }
        return errors
    }

}