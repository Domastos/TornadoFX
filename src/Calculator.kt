import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.control.Button
import tornadofx.*
import java.io.StringWriter
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
                    "*", "/", "(", ")", "^", "%" -> extendExpression(op)
                    "=" -> doEvaluation()
                    "DEL" ->  display.text = display.text.dropLast(1)
                    "C" -> display.text = ""
                    "CE" -> clearExpression()
                    "π" -> display.text += "π"
                    "e" -> display.text += "e"
                    }
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
                display.text.isEmpty() -> {
                    display.text += op
                }
                else -> {
                    extendExpression(op)
                }
            }
        } catch (e: NoSuchElementException) {
            extendExpression(op)
        }
    }

    private fun extendExpression(op: String) {
        if (display.text != "") {
            expressionList.add(display.text)
            expressionList.add(op)
        }
        else {
            expressionList.add(op)
        }
        displayExpression.text += display.text
        displayExpression.text += op
        display.text = ""
    }

    private fun doEvaluation(){
        expressionList.add(display.text)
        displayExpression.text += display.text
        evaluate(expressionList)
    }

    private fun clearExpression() {
        expressionList.clear()
        displayExpression.text = ""
    }


}
