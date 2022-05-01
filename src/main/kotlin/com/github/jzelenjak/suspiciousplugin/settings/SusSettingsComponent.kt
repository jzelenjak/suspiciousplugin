package com.github.jzelenjak.suspiciousplugin.settings

import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import javax.swing.JCheckBox
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTextField

/**
 * This class is similar to a View because it displays and captures edits to the values of the Settings.
 */
class SusSettingsComponent {
    var panel: JPanel? = null;
    private var numImpostorsTextField = JTextField("3");
    private var doYouLikeAmongUsCheckBox = JCheckBox("Do you like Among Us? ");

    init {
        panel = FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Number of impostors: "), numImpostorsTextField, 1, false)
            .addComponent(doYouLikeAmongUsCheckBox, 1)
            .addComponentFillVertically(JPanel(), 0)
            .panel;
    }

    /**
     * Returns the UI component that should be focused when a user opens the Suspicious Settings page.
     */
    fun getPreferredFocusedComponent(): JComponent {
        return numImpostorsTextField;
    }


    var numImpostors: String?
        get() = numImpostorsTextField.text;
        set(newText) {
            numImpostorsTextField.text = newText;
        }

    var doYouLikeAmongUs: Boolean
        get() = doYouLikeAmongUsCheckBox.isSelected;
        set(newStatus) {
            doYouLikeAmongUsCheckBox.isSelected = newStatus;
        }
}