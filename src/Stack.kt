//import java.util.*
//
//fun Calculator.stack(x: String) {
//    //sprawdzenie czy niema dzielenia na 0
//    if (division_error) {
//        display1.text = ""
//        division_error = false
//    }
//    //sprawdzenie czy coś było wprowadzone
//    if (shown_result) {
//        display.text = ""
//        shown_result = false
//    }
//    //oczyszczenie linii wprowadzenie w przypadku wprowadzenia zanku równości
//    if (display1.text.contains("=")) {
//        clean()
//    }
//    //dodawanie textu na linje
//    if (Regex("[0-9.]").matches(x)) {
//        if (x == "." && display.text.isEmpty()) display.text += "0$x"
//        if (!(x == "." && display.text.contains("."))) display.text += x
//    }
//
//    else {
//        //Zamiana String na Char
//        if (x != "+/-")
//            sign_ = x[0]
//        if (display.text.isEmpty() && x == "+/-") return
//        if (display.text.isEmpty() && display1.text.isEmpty() && (x[0] in operator_array || sign_ == ')')) return
//        if (display.text.isEmpty() && display1.text.isNotEmpty() && sign_ == ')')
//            if ((display1.text.count { c: Char -> c == '(' } < (display1.text.count { c: Char -> c == ')' } + 1))) return
//        if (x == "C" || x == "CE") {
//            clean()
//        }
//        else {
//            //Zapobiega wpisanu paru znakow zaraz po sobie
//            if (display.text.isEmpty() && display1.text.isNotEmpty())
//                if (sign_ in operator_array && display1.text.last() in operator_array) return
//            if (x == "+/-") {
//                if (display.text.contains('.')) {
//                    val temp1: Double = display.text.toDouble()
//                    val temp2: Double = -temp1
//                    display.text = temp2.toString()
//                } else {
//                    val temp3: Int = display.text.toInt()
//                    val temp4: Int = -temp3
//                    display.text = temp4.toString()
//                }
//                return
//            }
//            else {
//                display1.text += display.text; display1.text += x
//            }
//        }
//        when (sign_) {
//            '+', '-' -> {
//                check_bracket()
//                display.text = ""
//                if (sign_ == '-' && display1.text[display1.text.lastIndex - 1] == '(') NumberStack.add(0.0)
//                if (!first_number) {
//                    if (SignStack.peek() in arrayOf('+', '-', '*', '/'))
//                        do operation()
//                        while (SignStack.isNotEmpty() && SignStack.peek() in arrayOf('+', '-'))
//                } else {
//                    first_number = false
//                }
//                SignStack.add(sign_)
//            }
//            '/', '*' -> {
//                check_bracket()
//                display.text = ""
//                if (!first_number) {
//                    if (SignStack.peek() in arrayOf('*', '/'))
//                        operation()
//                } else first_number = false
//                SignStack.add(sign_)
//            }
//            '=' -> {
//                check_bracket()
//                display.text = ""
//                if (SignStack.isEmpty() && NumberStack.size == 1) {
//                    display1.text += NumberStack.peek()
//                    return
//                }
//                do operation()
//                while (SignStack.isNotEmpty())
//                rest()
//            }
//            ')' -> {
//                check_bracket()
//                display.text = ""
//
//                if (SignStack.peek() != '(') {
//                    do operation()
//                    while (SignStack.isNotEmpty() && SignStack.peek() != '(')
//                } else {
//                    if (SignStack.isEmpty()) {
//                        display.text = NumberStack.peek().toString()
//                    }
//shown_result = true
//}
//if (SignStack.isNotEmpty()) {
//    SignStack.pop()
//}
//show_log()
//if (SignStack.size == 1) {
//    first_number = true
//}
//}
//'(' -> {
//    SignStack.add(sign_)
//}
//}
//}
//}


fun Calculator.precedence(op: String): Int {
    return when (op) {
        "+", "-" -> 1
        "*", "/" -> 2
        else -> { // Note the block
            print("op is on list yet")
            0;
        }
    }
}

fun Calculator.applyOperation(a: Double, b: Double, op: String): Double {
    return when (op) {
        "+" -> a + b
        "-" -> a - b
        "*" -> a * b
        "/" -> a / b
        else -> { // Note the block
            print("op is on list yet")
            0.0;
        }
    }
}

fun Calculator.evaluate(expression: String) {

    for (token in expression) {
        if (token == '(') {
            opStack.add(token)
        } else if (Character.isDigit(token)) {
            val number = StringBuilder()
            while (count<expression.length && (Character.isDigit(token) || token == '.')) {
                number.append(token)
                count++
            }

        }

    }
    display.text += "odpowiedź"
}


fun Calculator.isOperator(character: Char): Boolean {
        return when (character) {
            '-', '+', '*', '/', '%', '^', 'v' -> true
            else -> false
        }
}


fun Calculator.addition(a: Double, b: Double): Double = a + b
fun Calculator.substitution(a: Double, b: Double): Double = a - b
fun Calculator.multiplication(a: Double, b: Double): Double = a * b
fun Calculator.division(a: Double, b: Double) : Double = a / b