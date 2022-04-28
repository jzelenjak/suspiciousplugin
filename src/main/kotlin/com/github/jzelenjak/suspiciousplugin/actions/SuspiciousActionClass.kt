package com.github.jzelenjak.suspiciousplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.editor.CaretModel
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.ui.Messages

/**
 * A suspicious action that is only enabled when some text (part of code) has been selected. Is intended to work on a class
 */
class SuspiciousActionClass : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        val editor : Editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val selectedText : String? = editor.caretModel.currentCaret.selectedText

        Messages.showInfoMessage(
            e.project,
            "Suspicious action for the class `${e.dataContext}`\nThe suspicious text is:\n$selectedText",
            "Suspicious Action for Class"
        )
    }

    override fun update(e: AnActionEvent) {
        val editor: Editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val caretModel : CaretModel = editor.caretModel

        e.presentation.isEnabledAndVisible = caretModel.currentCaret.hasSelection()
    }
}