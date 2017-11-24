package com.bignerdranch.android.beatbox;

/**
 * Created by ProfessorTaha on 11/22/2017.
 */

public class Sound {
    private String mAssetPath;
    private String mName;
    private Integer ID;

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];
        mName = filename.replace(".wav", "");

    }

    public String getAssetPath() {
        return mAssetPath;
    }

    public String getName() {
        return mName;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

}
