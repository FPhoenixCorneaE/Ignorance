package com.maiml.library.rmswitch;

/**
 * Created by Riccardo Moro on 19/08/2016.
 */
interface TristateCheckable {
    void toggle();

    void setState(@RMTristateSwitch.State int state);

    @RMTristateSwitch.State
    int getState();
}
