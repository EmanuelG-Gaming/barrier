package barrier.content;

import mindustry.ctype.*;
import mindustry.type.*;
import barrier.content.BPlanets;

public class BSectorPresets implements ContentList{
    public static SectorPreset ignorance;

    @Override
    public void load(){
        //region shut

        ignorance = new SectorPreset("ignorance", BPlanets.sUp, 36){{
            alwaysUnlocked = true;
            captureWave = 10;
            difficulty = 1;
        }};
    }
}