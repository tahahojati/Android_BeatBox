package com.bignerdranch.android.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by ProfessorTaha on 11/22/2017.
 */

public class SoundViewModel extends BaseObservable {
    private static final String TAG = "SoundViewModel";
    private Sound mSound;
    private BeatBox mBeatBox;

    public SoundViewModel(BeatBox beatBox) {
        mBeatBox = beatBox;
    }

    public Sound getSound() {
        return mSound;
    }

    public void setSound(Sound sound) {
        mSound = sound;
        notifyChange();
    }

    @Bindable
    public String getTitle() {
        return mSound.getName();
    }

    public void onButtonClicked() {
        //Log.i(TAG, "onButtonClicked was called");
        mBeatBox.play(mSound);
    }

}
