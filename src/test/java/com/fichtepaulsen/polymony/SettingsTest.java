package com.fichtepaulsen.polymony;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SettingsTest {
    
    public SettingsTest() {
    }

    @Test
    public void testSettings() {
        Settings.createInstance();
        
        assertNotNull(Settings.getInstance());
        
        Settings.destroyInstance();
    }
}
