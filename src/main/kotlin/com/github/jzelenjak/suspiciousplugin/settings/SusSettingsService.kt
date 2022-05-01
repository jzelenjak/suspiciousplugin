package com.github.jzelenjak.suspiciousplugin.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

/**
 * This class is like a Model because it stores the Settings persistently
 */
@State(name = "SusSettingsState", storages = [Storage("suspiciousSettings.xml")])
class SusSettingsService : PersistentStateComponent<SusSettingsState> {

    private var testGenieSettingsState: SusSettingsState = SusSettingsState();
    /**
     * Gets the currently persisted state. This method is called every time the settings values are saved.
     * If the values from getState are different from the default values obtained by calling the default constructor,
     *   the state is persisted (serialised and stored).
     */
    override fun getState(): SusSettingsState {
        return testGenieSettingsState;
    }

    /**
     * Loads the state. This method is called after the settings component has been created and if the XML file with the state is changes externally.
     *  In the latter case, the component is responsible for updating the UI and other related components according to the changed state.
     */
    override fun loadState(state: SusSettingsState) {
        testGenieSettingsState = state;
    }


    /**
     * Returns the service object with a static call.
     */
    companion object {
        @JvmStatic
        fun getInstance(): PersistentStateComponent<SusSettingsState> {
            return ApplicationManager.getApplication().getService(SusSettingsService::class.java);
        }
    }
}