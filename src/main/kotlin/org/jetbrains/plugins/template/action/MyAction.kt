package org.jetbrains.plugins.template.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent

class MyAction : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        println("Hello, world!")
    }

}
