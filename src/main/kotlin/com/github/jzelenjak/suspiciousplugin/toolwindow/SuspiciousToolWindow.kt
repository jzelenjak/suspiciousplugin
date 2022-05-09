package com.github.jzelenjak.suspiciousplugin.toolwindow

import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.wm.ToolWindow
import java.awt.event.ActionEvent
import javax.swing.*

/**
 * A class for the tool window of the plugin
 */
class SuspiciousToolWindow(_toolWindow: ToolWindow, _project: Project) {

    val toolWindow : ToolWindow = _toolWindow;
    val project : Project = _project;

    // number of impostors, difficultyTextField level, is the impostor(s) red? - some parameters
    private var numImpostorsTextField: JTextField? = null
    private var difficultyTextField: JTextField? = null
    private var isImpostorRedCombobox: JComboBox<Boolean>? = null
    private var saveButton: JButton? = null
    private var advancedSettingsButton: JButton? = null

    private var toolWindowPanel: JPanel? = null

    init {
        saveButton?.addActionListener { addListenerForSaveButton(it) }
        advancedSettingsButton?.addActionListener { addListenerForAdvancedSettingsButton(it) }
    }

    /**
     * Get the panel with everything in it
     */
    fun getContent(): JComponent? {
        return toolWindowPanel;
    }

    /**
     * Add a listener to the save button to validate the input and get the actual input
     */
    private val addListenerForSaveButton : (ActionEvent) -> Unit = {
        if (numImpostorsTextField?.text == null || difficultyTextField?.text == null) {
            Messages.showErrorDialog("Please specify the value", "Empty Value Field")
        } else {
            // Validate all the input values
            val numImpostors: Int? = toInt(numImpostorsTextField!!.text)
            val difficulty: Int? = toInt(difficultyTextField!!.text)
            val isImpostorRed : Boolean = isImpostorRedCombobox?.selectedItem.toString().toBoolean()

            if (numImpostors == null || difficulty == null) {
                Messages.showErrorDialog("Please specify a number", "Invalid Input Value")
            } else {
                Messages.showInfoMessage(
                    "Settings are saved" +
                            "\nNumber of impostors: $numImpostors" +
                            "\nDifficulty level: $difficulty" +
                            "\nIs the impostor red: $isImpostorRed",
                    "Saved"
                )
            }
        }
    }

    private val addListenerForAdvancedSettingsButton : (ActionEvent) -> Unit = {
        ShowSettingsUtil.getInstance().showSettingsDialog(project, "Suspicious")
    }

    /**
     * A helper method to safely convert an input string to a (nullable) integer
     */
    private fun toInt(str: String): Int? {
        return try {
            str.toInt()
        } catch (e : java.lang.NumberFormatException) {
            null
        }
    }

}