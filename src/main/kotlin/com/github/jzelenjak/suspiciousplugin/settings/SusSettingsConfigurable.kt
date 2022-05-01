package com.github.jzelenjak.suspiciousplugin.settings

import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

/**
 * This class is analogous to a Controller in the MVC model - it interacts with the other two Settings classes and the IntelliJ Platform.
 *
 * Once instantiated, a Configurable instance's lifetime continues regardless of whether the implementation's Settings are changed,
 *   or the user chooses a different entry on the Settings Dialog menu.
 * A Configurable instance's lifetime ends when OK or Cancel is selected in the Settings Dialog.
 *   An instance's Configurable.disposeUIResources() is called when the Settings Dialog is closing.
 */
class SusSettingsConfigurable : Configurable {
    private var settingsComponent: SusSettingsComponent? = null;

    /**
     * Creates a settings component that holds the panel with the settings entries, and returns this panel
     */
    override fun createComponent(): JComponent? {
        settingsComponent = SusSettingsComponent();
        return settingsComponent!!.panel;
    }

    /**
     * Sets the stored state values to the corresponding UI components.
     *  This method is invoked immediately after Configurable.createComponent().
     *   Initialization of Setting values in the constructor or createComponent() is unnecessary.
     */
    override fun reset() {
        val settingsState: SusSettingsState = SusSettingsService.getInstance().state!!;
        settingsComponent!!.numImpostors = settingsState.numImpostors;
        settingsComponent!!.doYouLikeAmongUs = settingsState.doYouLikeAmongUs;
    }

    /**
     * Checks if the values of the entries in the settings state are different from the persisted values of these entries.
     */
    override fun isModified(): Boolean {
        val settingsState: SusSettingsState = SusSettingsService.getInstance().state!!;
        var modified: Boolean = settingsComponent!!.numImpostors != settingsState.numImpostors;
        modified = modified or (settingsComponent!!.doYouLikeAmongUs != settingsState.doYouLikeAmongUs);
        return modified;
    }

    /**
     * Persists the modified state after a user hit Apply button.
     */
    override fun apply() {
        val settingsState: SusSettingsState = SusSettingsService.getInstance().state!!;
        settingsState.numImpostors = settingsComponent!!.numImpostors!!;
        settingsState.doYouLikeAmongUs = settingsComponent!!.doYouLikeAmongUs;
    }

    /**
     * Returns the displayed name of the Settings tab.
     */
    override fun getDisplayName(): String {
        return "Suspicious";
    }

    /**
     * Returns the UI component that should be focused when the Sus Settings page is opened.
     */
    override fun getPreferredFocusedComponent(): JComponent {
        return settingsComponent!!.getPreferredFocusedComponent();
    }

    /**
     * Disposes the UI resources. It is called when a user closes the Settings dialog.
     */
    override fun disposeUIResources() {
        settingsComponent = null;
    }
}