package com.github.jzelenjak.suspiciousplugin.actions

import com.github.jzelenjak.suspiciousplugin.SusIdeaDataDialogWrapper
import com.github.jzelenjak.suspiciousplugin.SusPopupList
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.popup.JBPopupFactory

class SuspiciousAction : AnAction() {
    // The important method that has to be overridden. Called whenever a user triggers the action
    override fun actionPerformed(e: AnActionEvent) {
        //showMessageDialog(e);
        //showFileDialog(e)
        //showCustomDialog(e);
        val susPopupList = SusPopupList("sus-idea-list", mutableListOf("apple", "pear", "banana"));
        if (e.project != null) {
            JBPopupFactory.getInstance().createListPopup(susPopupList, 5).showCenteredInCurrentWindow(e.project!!); // `!!` is a non-null assertion
        }
    }

    // Shows a menu dialog with whatever info you put there
    private fun showMessageDialog(e: AnActionEvent) {
        // specify the project, message, title and the icon
        Messages.showMessageDialog(e.project, "SUS", "Amogus", Messages.getInformationIcon())
    }

    // Shows a file dialog that
    private fun showFileDialog(e: AnActionEvent) {
        // create a file chooser descriptor with parameters: `chooseFiles`, `chooseFolders`, `chooseJars`, `chooseJarsAsFiles`, `chooseJarContents`, `chooseMultiple`
        val fileChooserDescriptor = FileChooserDescriptor(false, true, false, false, false, false)
        fileChooserDescriptor.title = "Suspicious Pick Directory";
        fileChooserDescriptor.description = "Suspicious chooser amogus";

        // Let the user choose a file, then execute a callback
        FileChooser.chooseFile(fileChooserDescriptor, e.project, null) {
            // Show message dialog with the following parameters: project, message, title and icon
            Messages.showMessageDialog(e.project, it.path, "Path", Messages.getInformationIcon())
        }
    }

    // Display a custom dialog. Here we will have a state for user's credentials
    private fun showCustomDialog(e: AnActionEvent) {
        val susIdeaDataDialogWrapper = SusIdeaDataDialogWrapper();

        // Check if the user have pressed ok
        if (susIdeaDataDialogWrapper.showAndGet()) {
            susIdeaDataDialogWrapper.close(23);
        }
    }

    // Display a question dialog asking for the username, then show a message
    private fun getUsername(e: AnActionEvent) {
        val name = Messages.showInputDialog(e.project,"Enter your name", "Suspicious Data", Messages.getQuestionIcon())
        Messages.showMessageDialog(e.project, "Vote kick $name", "SUS", Messages.getInformationIcon())
    }
}