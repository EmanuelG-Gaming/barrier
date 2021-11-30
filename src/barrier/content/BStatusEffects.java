package barrier.content;

import mindustry.*;
import mindustry.graphics.*; 
import mindustry.ctype.*;
import mindustry.type.*;
import barrier.content.BFx;

public class BStatusEffects implements ContentList{
    public static StatusEffect
    
    severed;
    
    @Override
    public void load(){
        severed = new StatusEffect("severed"){{
            hideDetails = false; // I mean, this only works in V7, iirc
            color = Pal.health;
            speedMultiplier = 1.3f;
            healthMultiplier = 0.6f;
            damage = 0.8f;
            effect = BFx.severedWounds;
        }};
    }
}