package com.github.jzelenjak.suspiciousplugin.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages

/**
 * A suspicious action that asks the user to enter their username and then displays it
 */
class SuspiciousAction5 : AnAction() {

    // The important method that has to be overridden. Called whenever a user triggers the action
    override fun actionPerformed(e: AnActionEvent) {
       getUsername(e);
    }

    // Display a question dialog asking for the username, then show a message
    private fun getUsername(e: AnActionEvent) {
        val name = Messages.showInputDialog(e.project,"Enter your name", "Suspicious Data", Messages.getQuestionIcon());
        Messages.showMessageDialog(e.project, "Vote kick $name", "SUS", Messages.getInformationIcon());
    }
}