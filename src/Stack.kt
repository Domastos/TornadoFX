import tornadofx.*
import java.lang.Double.parseDouble
import kotlin.math.pow

fun Calculator.precedence(op: String): Int {
    return when (op) {
        "+", "-" -> 1
        "*", "/", "%" -> 2
        "^", "v" -> 3
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
        "^" -> a.pow(b)
        "%" -> a % b
        else -> { // Note the block
            print("op is on list yet")
            0.0;
        }
    }
}

fun Calculator.evaluate(expression: MutableList<String>) {
    display.text = ""
    for (token in expression) {
        when{
            token == "(" -> {
                opStack.push(token)
            }
            token == ")" -> {
                while (opStack.size != 0 && opStack.peek() != "(") {
                    val val2 = valStack.pop()
                    val val1 = valStack.pop()
                    val op = opStack.pop()
                    valStack.add(applyOperation(val1, val2, op))
                }
                opStack.pop()
            }
            token.isDouble() -> {
                valStack.push(parseDouble(token))
            }
            token == "π" -> {
                valStack.push(Math.PI)
            }
            token == "e" -> {
                valStack.push(Math.E)
            }
            isOperator(token) -> {
                opStack.push(token)
            }
            else -> {
                while(opStack.size != 0 && precedence(opStack.peek()) >= precedence(token))
                {
                    val val2 = valStack.pop()
                    val val1 = valStack.pop()
                    val op = opStack.pop()
                    valStack.add(applyOperation(val1, val2, op))
                }
                opStack.push(token)
            }
        }
    }

    print(expressionList)
    print(valStack)
    print(opStack)

    while (opStack.size != 0) {
        val val2 = valStack.pop()
        val val1 = valStack.pop()
        val op = opStack.pop()
        valStack.add(applyOperation(val1, val2, op))
    }
    display.text += valStack.peek().toString()
    expressionList.clear()
}





fun Calculator.isOperator(character: String): Boolean {
        return when (character) {
            "-", "+", "*", "/", "%", "^", "v" -> true
            else -> false
        }
}
