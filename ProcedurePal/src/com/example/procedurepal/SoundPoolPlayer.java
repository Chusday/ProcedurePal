package com.example.procedurepal;

import java.util.HashMap;

import com.example.myfirstapp.R;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundPoolPlayer {
    private SoundPool mShortPlayer= null;
    private HashMap mSounds = new HashMap();

    public SoundPoolPlayer(Context pContext)
    {
        // setup Soundpool
        this.mShortPlayer = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);


        mSounds.put(R.raw.laundry_step_1, this.mShortPlayer.load(pContext, R.raw.laundry_step_1, 1));
        mSounds.put(R.raw.laundry_step_2, this.mShortPlayer.load(pContext, R.raw.laundry_step_2, 1));
        mSounds.put(R.raw.laundry_step_3, this.mShortPlayer.load(pContext, R.raw.laundry_step_3, 1));
        mSounds.put(R.raw.laundry_step_4, this.mShortPlayer.load(pContext, R.raw.laundry_step_4, 1));
        mSounds.put(R.raw.laundry_step_5, this.mShortPlayer.load(pContext, R.raw.laundry_step_5, 1));
        mSounds.put(R.raw.laundry_step_6, this.mShortPlayer.load(pContext, R.raw.laundry_step_6, 1));
        mSounds.put(R.raw.laundry_step_7, this.mShortPlayer.load(pContext, R.raw.laundry_step_7, 1));
        mSounds.put(R.raw.laundry_step_8, this.mShortPlayer.load(pContext, R.raw.laundry_step_8, 1));
        mSounds.put(R.raw.laundry_step_9, this.mShortPlayer.load(pContext, R.raw.laundry_step_9, 1));
        mSounds.put(R.raw.laundry_step_10, this.mShortPlayer.load(pContext, R.raw.laundry_step_10, 1));
    }

    public void playShortResource(int piResource) {
        int iSoundId = (Integer) mSounds.get(piResource);
        this.mShortPlayer.play(iSoundId, 0.99f, 0.99f, 0, 0, 1);
    }

    // Cleanup
    public void release() {
        // Cleanup
        this.mShortPlayer.release();
        this.mShortPlayer = null;
    }
}