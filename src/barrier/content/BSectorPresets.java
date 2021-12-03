package barrier.content;

import mindustry.ctype.*;
import mindustry.type.*;

import static barrier.content.BPlanets;

public class BSectorPresets implements ContentList{
    public static SectorPreset ignorance;

    @Override
    public void load(){
        //region shut

        ignorance = new SectorPreset("ignorance", sUp, 14){{
            alwaysUnlocked = true;
            addStartingItems = true;
            captureWave = 10;
            difficulty = 1;
        }};
    }
}