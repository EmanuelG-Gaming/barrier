package barrier.content;

import mindustry.graphics.*; 
import mindustry.ctype.*;
import mindustry.type.*;

public class BStatusEffects implements ContentList{
    public static StatusEffect
    
    severed;
    
    @Override
    public void load(){
        severed = new StatusEffect("severed"){{
            color = Pal.health;
            speedMultiplier = 0.6f;
            healthMultiplier = 0.8f;
            isHidden(){
               return false;
            };
        }};
    };
}