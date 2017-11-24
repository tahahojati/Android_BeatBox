package com.bignerdranch.android.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProfessorTaha on 11/22/2017.
 */

class BeatBox {
    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_STREAMS = 5;
    private AssetManager mAssets;
    private List<Sound> mSounds;
    private SoundPool mSoundPool;
    private float mPlaybackSpeed;

    public BeatBox(Context context) {
        mAssets = context.getAssets();
        mPlaybackSpeed = 1.0f;
        mSounds = new ArrayList<Sound>();
        mSoundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    public void loadSounds() {
        String[] soundNames;
        mSounds.clear();
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.i(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException e) {
            Log.e(TAG, "Count not list assets", e);
            return;
        }
        for (String fileName : soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + fileName;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException e) {
                Log.e(TAG, "Could not load sound " + fileName, e);
            }
        }
    }

    public float getPlaybackSpeed() {
        return mPlaybackSpeed;
    }

    public void setPlaybackSpeed(float mPlaybackSpeed) {
        this.mPlaybackSpeed = mPlaybackSpeed;
    }

    public void load(Sound sound) throws IOException {
        AssetFileDescriptor afd = mAssets.openFd(sound.getAssetPath());
        int soundId = mSoundPool.load(afd, 1);
        sound.setID(soundId);
    }

    public List<Sound> getSounds() {
        return mSounds;
    }

    public void play(Sound sound) {
        Integer id = sound.getID();
        if (id == null) {
            Log.w(TAG, "sound id was null");
            return;
        }
        mSoundPool.play(id, 1.0f, 1.0f, 1, 0, getPlaybackSpeed());
        Log.i(TAG, "Sound was played");
    }

    public void release() {
        mSoundPool.release();
    }
}
