package com.github.jzelenjak.suspiciousplugin.toolwindow

import com.intellij.openapi.ui.Messages
import com.intellij.openapi.wm.ToolWindow
import java.awt.event.ActionEvent
import javax.swing.*

/**
 * A class for the tool window of the plugin
 */
class SuspiciousToolWindow(_toolWindow: ToolWindow) {

    val toolWindow : ToolWindow = _toolWindow;

    // number of impostors, difficultyTextField level, is the impostor(s) red? - some parameters
    private var numImpostorsTextField: JTextField? = null
    private var difficultyTextField: JTextField? = null
    private var isImpostorRedCombobox: JComboBox<Boolean>? = null
    private var saveButton: JButton? = null

    private var toolWindowPanel: JPanel? = null

    init {
        saveButton?.addActionListener { addListenerForSaveButton(it) }
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