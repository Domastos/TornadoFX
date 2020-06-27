//fun Calculator.clean() {
//    display.text = ""
//    displayExpression.text = ""
//    NumberStack.clear()
//    SignStack.clear()
//    first_number = true
//}
//
//fun Calculator.check_bracket(){
//    //Przed ostatni znak
//    if(displayExpression.text.isNotEmpty() && display.text.isNotEmpty())
//        if(displayExpression.text[displayExpression.text.lastIndex - 1] != ')') { NumberStack.add(displayValue) }
//}
//
//fun Calculator.show_log(){
//    //pokaż log
//    println("\t NumberStack: $NumberStack")
//    println("\t SignStack: $SignStack")
//}

//fun Calculator.rest(){
//    if(SignStack.isEmpty() && NumberStack.isNotEmpty() && NumberStack.size > 1) {
//        do{
//            show_log()
//            display.text = NumberStack.push(addition(NumberStack.pop(), NumberStack.pop())).toString()
//            show_log()
//        }
//        while(NumberStack.isEmpty())
//    }
//}
//
//fun Calculator.operation() {
//    if(SignStack.isNotEmpty() && NumberStack.isNotEmpty()) {
//        show_log()
//        when (SignStack.pop()) {
//            '+' -> display.text = NumberStack.push(addition(NumberStack.pop(), NumberStack.pop())).toString()
//            '-' -> display.text = NumberStack.push(substitution(NumberStack.pop(), NumberStack.pop())).toString()
//            '/' -> display.text = NumberStack.push(division(NumberStack.pop(), NumberStack.pop())).toString()
//            '*' -> display.text = NumberStack.push(multiplication(NumberStack.pop(), NumberStack.pop())).toString()
//
////                "%" -> { onAction(add(displayValue /100)); operator("=") }
////                "X" -> onAction(mult(displayValue))
////                "C" -> onAction(add(0))
////                "CE" -> onAction(add(0))
////                "+/-" -> { onAction(add(-1* displayValue)); operator("=") }
////                "=" -> display.text = state.calculate(displayValue).toString()
////                "|x|" -> onAction(modul(displayValue))
////                "x²" -> onAction(square(displayValue))
////                "√x" -> onAction(root(displayValue))
//        }
//        shown_result = true
//        show_log()
//        rest()
//    }
//}