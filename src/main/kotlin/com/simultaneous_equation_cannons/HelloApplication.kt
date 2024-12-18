package com.simultaneous_equation_cannons

import javafx.application.Application
import javafx.scene.Scene
import javafx.geometry.Insets;
import javafx.geometry.Pos
import javafx.scene.control.Button;
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Priority
import javafx.scene.layout.VBox
import javafx.stage.Stage
import org.kordamp.bootstrapfx.BootstrapFX
import org.kordamp.bootstrapfx.scene.layout.Panel


class HelloApplication : Application() {
    override fun start(stage: Stage) {
        val panel: Panel = Panel("연율포고정식 - by KJT")
        panel.styleClass.add("panel-primary")
        val content = BorderPane()
        content.padding = Insets(30.0)


        //레이블
        val fieldAndHandsLabel = Label("양 플레이어의 패 / 필드의 카드 수를 입력하세요.")
        val fieldMonsterLevelLabel = Label("상대 몬스터 1장의 레벨을 입력하세요.")
        val resultXyzLabel = Label("엑스트라 덱에서 제외할 엑시즈의 몬스터 랭크")
        val resultFusionLabel = Label("엑스트라 덱에서 제외할 융합 몬스터의 레벨")
        val resultXyzRankLabel = Label()
        val resulTFusionLevelLabel = Label()
        fieldAndHandsLabel.styleClass.setAll("strong","h1","lbl","lbl-default",)
        fieldAndHandsLabel.padding = Insets(3.0)
        fieldMonsterLevelLabel.styleClass.setAll("lbl","lbl-default","b")
        fieldMonsterLevelLabel.padding = Insets(3.0)
        resultXyzLabel.styleClass.setAll("lbl","lbl-warning")
        resultFusionLabel.styleClass.setAll("lbl","lbl-warning")
        fieldAndHandsLabel.style = "-fx-font-size: 14px; -fx-font-weight: bold;"
        fieldMonsterLevelLabel.style = "-fx-font-size: 14px; -fx-font-weight: bold;"
        resultXyzLabel.style = "-fx-font-size: 14px;  -fx-font-weight: bold;"
        resultFusionLabel.style = "-fx-font-size: 14px; -fx-font-weight: bold;"
        resultXyzRankLabel.styleClass.setAll("h3")
        resulTFusionLevelLabel.styleClass.setAll("h3")

        //텍스트
        val fieldAndHandsTextField = TextField()
        val fieldMonsterLevelTextField = TextField()
        onlyNumber(fieldAndHandsTextField)
        onlyNumber(fieldMonsterLevelTextField)

        //버튼
        val button: Button = Button("계산하기")
        button.alignment = Pos.CENTER
        button.styleClass.setAll("btn", "btn-success")
        //버튼 이벤트 설정
        button.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED){
            event ->
                if(fieldMonsterLevelTextField.text.isNullOrEmpty() || fieldAndHandsTextField.text.isNullOrEmpty()){
                    event.consume()
                }
            else{
                    val xyxLevel = fieldAndHandsTextField.text.toInt() - fieldMonsterLevelTextField.text.toInt()
                    resultXyzRankLabel.text = xyxLevel.toString()
                    val fusionLevel =   fieldMonsterLevelTextField.text.toInt().times(2) - fieldAndHandsTextField.text.toInt()
                    resulTFusionLevelLabel.text = fusionLevel.toString()
                }
        }

        //컴포넌트 배치
        val topContent = VBox(10.0,fieldAndHandsLabel,fieldAndHandsTextField,fieldMonsterLevelLabel,fieldMonsterLevelTextField,button)
        val centerContent = VBox(10.0,resultXyzLabel,resultXyzRankLabel,resultFusionLabel,resulTFusionLevelLabel)
        topContent.isFillWidth = true // VBox 너비를 부모에 맞게 설정
        topContent.alignment = Pos.CENTER
        centerContent.alignment = Pos.CENTER
        VBox.setMargin(button,Insets(20.0,0.0,30.0,0.0))

        // TextField와 Button의 너비를 부모에 맞게 확장
        fieldAndHandsTextField.maxWidth = Double.MAX_VALUE
        fieldMonsterLevelTextField.maxWidth = Double.MAX_VALUE
        button.maxWidth = Double.MAX_VALUE

        // 높이 확장 설정
        VBox.setVgrow(fieldAndHandsTextField, Priority.ALWAYS)
        VBox.setVgrow(fieldMonsterLevelTextField, Priority.ALWAYS)
        VBox.setVgrow(button, Priority.ALWAYS)
        content.top = topContent
        content.center = centerContent
        panel.body = content

        val scene = Scene(panel,450.0,450.0)
        scene.stylesheets.add(BootstrapFX.bootstrapFXStylesheet()) //(3)

        stage.title = "연율포고정식"
        stage.scene = scene
        stage.sizeToScene()
        stage.isResizable = false
        stage.show()
    }


    fun onlyNumber(textField: TextField){
        textField.addEventHandler(javafx.scene.input.KeyEvent.KEY_TYPED){
            event ->
            val input = event.character
            if(!input.matches("[0-9]".toRegex())) event.consume()
        }
    }
}

fun main() {
    Application.launch(HelloApplication::class.java)
}