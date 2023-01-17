package com.springdemo.helloworld.Config;

import javax.sound.midi.Track;

public class FactoryManager {
    private static FactoryManager instance = null;
    private FactoryManager() {}
    public static FactoryManager getInstance()
    {
        if (instance == null)
        {
            instance = new FactoryManager();
        }
        return instance;
    }
}
