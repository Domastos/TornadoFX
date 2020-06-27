import javafx.fxml.FXML
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.scene.control.Button
import tornadofx.*
import java.util.*

class Calculator : View() {

    override val root: VBox by fxml()

    @FXML
    lateinit var display: Label

    @FXML
    lateinit var displayExpression: Label

    val operator_array = charArrayOf('+', '-', '*', '/')
    var first_number: Boolean = true
    var shown_result: Boolean = false
    var division_error: Boolean = false
    var sign_: Char = '\u0000'

    var NumberStack = Stack<Double>()
    var SignStack = Stack<Char>()

    //**********************************************************************
    var valStack = Stack<Double>()
    var opStack = Stack<Char>()
    var isNegative = false
    var expectOperator = false
    var count = 0


    init {
        title = "Calculator"
        root.lookupAll(".button").forEach { button ->
            button.setOnMouseClicked {
//                stack((button as Button).text)
                checkOperator((button as Button).text)
            }
        }
    }

    val displayValue: Double
        get() = when (display.text) {
            "" -> 0.0
            else -> display.text.toDouble()
        }


    fun checkOperator(op: String) {
        if (Regex("\\d|\\.").matches(op)) {
            checkNumSyntax(op)
        } else if (Regex("[+-]").matches(op)) {
            checkifSign(op)
        }
        else {
            when (op) {
                "*", "/", "(", ")", "^", "%" -> extendExpression(op)
                "=" -> evaluate(displayExpression.text)
            }
        }
    }

    fun checkifSign(op: String) {
        if (display.text.isEmpty()) {
            display.text += op
        } else {
            extendExpression(op)
        }
    }

    fun checkNumSyntax(op: String): Int {
        if ((Regex("[+-]?\\d+\\.\\d*").matches(display.text) && op == ".") || (display.text.isEmpty() && op == ".")) {
            return 1
        } else {
            display.text += op
            return 2
        }
    }

    fun extendExpression(op: String) {
        displayExpression.text += display.text
        displayExpression.text += op
        display.text = ""
    }


}
