package com.github.jzelenjak.suspiciousplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

/**
 * A suspicious action that shows a simple message dialog
 */
class SuspiciousAction1 : AnAction() {

    // The important method that has to be overridden. Called whenever a user triggers the action
    override fun actionPerformed(e: AnActionEvent) {
        showMessageDialog(e);
    }

    // Shows a menu dialog with whatever info you put there
    private fun showMessageDialog(e: AnActionEvent) {
        // specify the project, message, title and the icon
        Messages.showMessageDialog(e.project, "SUS", "Amogus", Messages.getInformationIcon());
    }

}