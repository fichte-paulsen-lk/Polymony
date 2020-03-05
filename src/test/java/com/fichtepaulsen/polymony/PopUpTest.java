package com.fichtepaulsen.polymony;

import javafx.stage.Stage;
import org.junit.Test;
import static org.junit.Assert.*;

public class PopUpTest {
    
    public PopUpTest() {
    }
    
    @Test
    public void test() { 
        PolyMonyPopup.stage = new Stage();
        PolyMonyPopup.show("Test?", (b) -> System.out.println(b));
    }
}

