import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.control.Button
import tornadofx.*
import java.util.*
import kotlin.NoSuchElementException

class Calculator : View() {

    override val root: VBox by fxml()

    @FXML
    lateinit var display: Label

    @FXML
    lateinit var displayExpression: Label

    var expressionList = mutableListOf<String>()
    var valStack = Stack<Double>()
    var opStack = Stack<String>()


    init {
        title = "Calculator"
        root.lookupAll(".button").forEach { button ->
            button.setOnMouseClicked {
                checkOperator((button as Button).text)
            }
        }
    }

    private fun checkOperator(op: String) {
        when {
            Regex("\\d|\\.").matches(op) -> {
                checkNumSyntax(op)
            }
            Regex("[+-]").matches(op) -> {
                checkifSign(op)
            }

            else -> {
                when (op) {
                    "*", "/", "(", ")", "^", "%", "v" -> extendExpression(op)
                    "1/x", "x²", "x³", "√x", "mod" -> evaluateInline(op)
                    "=" -> doEvaluation()
                    "DEL" -> display.text = display.text.dropLast(1)
                    "C" -> display.text = ""
                    "CE" -> clearExpression()
                    "π" -> display.text += "π"
                    "e" -> display.text += "e"
                }
            }
        }
    }


    private fun evaluateInline(op: String) {
        when (op) {
            "1/x" -> {
                expressionList.add("1")
                expressionList.add("/")
                expressionList.add(display.text)

                val x = display.text
                display.text = ""
                display.text += ("1/$x")
            }
            "x²" -> {
                expressionList.add(display.text)
                expressionList.add("^")
                expressionList.add("2")

                val x = display.text
                display.text = ""
                display.text += ("$x²")
            }
            "x³" -> {
                expressionList.add(display.text)
                expressionList.add("^")
                expressionList.add("3")

                val x = display.text
                display.text = ""
                display.text += ("$x³")
            }
            "mod" -> {
                val x = display.text
                expressionList.add(display.text.removePrefix("-"))
                display.text = ""
                display.text += ("|$x|")
            }

        }

    }


    private fun checkNumSyntax(op: String): Int {
        return if ((Regex("[+-]?\\d+\\.\\d*").matches(display.text) && op == ".") || (display.text.isEmpty() && op == ".")) {
            1
        } else {
            display.text += op
            2
        }
    }

    private fun checkifSign(op: String) {
        try {
            when {
                displayExpression.text.last() == ')' -> {
                    extendExpression(op)
                }
                isOperator(expressionList.last()) && display.text == "" -> {
                    expressionList.removeLast()
                    expressionList.add(op)
                    displayExpression.text = displayExpression.text.dropLast(1)
                    displayExpression.text += op
                }
                display.text.isEmpty() -> {
                    display.text += op
                }
                else -> {
                    extendExpression(op)
                }
            }
        } catch (e: NoSuchElementException) {
            when {
                display.text.isEmpty() -> {
                    display.text += op
                }
                else -> {
                    extendExpression(op)
                }
            }
        }
    }

    private fun extendExpression(op: String) {
        try {
            when {
                Regex("[+-]?1/\\d|\\d²|\\d³|√\\d|\\|[+-]?\\d\\|").matches(display.text) -> {
                    expressionList.add(op)
                    displayExpression.text += display.text
                    displayExpression.text += op
                    display.text = ""
                }
                isOperator(expressionList.last()) && display.text == "" -> {
                    expressionList.removeLast()
                    expressionList.add(op)
                    displayExpression.text = displayExpression.text.dropLast(1)
                    displayExpression.text += op
                }
//                expressionList.last() == "("
                display.text != "" -> {
                    expressionList.add(display.text)
                    expressionList.add(op)
                    displayExpression.text += display.text
                    displayExpression.text += op
                    display.text = ""
                }
                else -> {
                    expressionList.add(display.text)
                    expressionList.add(op)
                    displayExpression.text += display.text
                    displayExpression.text += op
                    display.text = ""
                }
            }
        } catch (e: NoSuchElementException) {
            expressionList.add(display.text)
            expressionList.add(op)
            displayExpression.text += display.text
            displayExpression.text += op
            display.text = ""
        }
    }

    private fun doEvaluation() {
        expressionList.add(display.text)
        displayExpression.text += display.text
        evaluate(expressionList)
    }

    private fun clearExpression() {
        expressionList.clear()
        displayExpression.text = ""
    }


}
