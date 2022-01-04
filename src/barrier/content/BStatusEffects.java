package barrier.content;

import mindustry.graphics.*; 
import mindustry.ctype.*;
import mindustry.type.*;
import barrier.content.BFx;

public class BStatusEffects implements ContentList{
    public static StatusEffect
    
    severed;
    
    @Override
    public void load() {
        severed = new StatusEffect("severed"){{
            hideDetails = false; // I mean, this only works in V7, iirc
            color = Pal.health;
            speedMultiplier = 1.4f;
            healthMultiplier = 0.65f;
            damage = 0.95f;
            effect = BFx.severedWounds;
        }};
    }
}