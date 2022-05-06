package com.github.jzelenjak.suspiciousplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.CaretModel
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

/**
 * A suspicious action that is only enabled when some text (part of code) has been selected. Is intended to work on a class
 */
class SuspiciousActionSelection : AnAction() {
    override fun actionPerformed(e: AnActionEvent) {
        // Get all the required data from data keys
        val editor : Editor = e.getRequiredData(CommonDataKeys.EDITOR);
        val project : Project? = e.project;
        // The Document represents the contents of a text file loaded into memory and opened in an IntelliJ Platform-based IDE editor.
        val document : Document = editor.document;

        val selectedText : String? = editor.caretModel.currentCaret.selectedText;

        Messages.showInfoMessage(
            e.project,
            "Suspicious action for the class `${e.dataContext}`\nThe suspicious text is:\n$selectedText",
            "Suspicious Action for Class"
        );

        when (Messages.showYesNoDialog("Do you want to replace the text?", "Replace Text?", Messages.getQuestionIcon())) {
            0 -> {
                // Work off of the primary caret to get the selection info
                val primaryCaret : Caret = editor.caretModel.primaryCaret;
                val start : Int = primaryCaret.selectionStart;
                val end : Int = primaryCaret.selectionEnd;

                Messages.showInfoMessage(
                    "Visual Position: ${primaryCaret.visualPosition}\nLogical Position: ${primaryCaret.logicalPosition}\nCaret offset: ${primaryCaret.offset}",
                    "Positions"
                );

                // Replace the selection with a fixed string.
                val susText : String = "SUS ".repeat(selectedText!!.length / 4);
                val susPad : String = " ".repeat(selectedText.length % 4);

                // Must do this document change in a write action context
                WriteCommandAction.runWriteCommandAction(project) { document.replaceString(start, end, susText + susPad) }

                // De-select the text range that was just replaced
                primaryCaret.removeSelection();
            }
            1 -> return;
        }
    }

    override fun update(e: AnActionEvent) {
        val editor: Editor = e.getRequiredData(CommonDataKeys.EDITOR)
        val caretModel : CaretModel = editor.caretModel

        e.presentation.isEnabledAndVisible = e.project != null && caretModel.currentCaret.hasSelection();  // or ` editor.selectionModel.hasSelection();`
    }
}