package com.github.jzelenjak.suspiciousplugin

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage

// This is an application service class
// The result will be stored in `./build/idea-sandbox/config/options/sus-idea-demo.xml` file
@State(name = "SusIdeaDemo", storages = [Storage("sus-idea-demo.xml")])
class SusIdeaDemoPluginSettings : PersistentStateComponent<SusIdeaDemoPluginState> {
    // Just put something other than state, since otherwise it will conflict with the getter for state (Kotlin thing)
    private var pluginState = SusIdeaDemoPluginState();

    override fun getState(): SusIdeaDemoPluginState {
        return pluginState;
    }

    override fun loadState(state: SusIdeaDemoPluginState) {
        pluginState = state;
    }

    // Basically, this is a way to call a static method `getInstance`. Singleton call
    // @JvmStatic is for mixing with java. Kotlin knows about its way of making things static
    companion object {
        @JvmStatic
        fun getInstance(): PersistentStateComponent<SusIdeaDemoPluginState> {
            return ServiceManager.getService(SusIdeaDemoPluginSettings::class.java)
        }
    }

}