import javafx.application.Application
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.VBox
import javafx.stage.Stage
import tornadofx.*

/*class MyApp: App(MyView::class)*/

class Application : App() {

    override val primaryView = Calculator::class

    override fun start(stage: Stage) {
        importStylesheet("/style.css")
        stage.isResizable = false
        super.start(stage)
            }
    }


