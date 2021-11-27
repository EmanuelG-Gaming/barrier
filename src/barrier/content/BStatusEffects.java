package barrier.content;

import arc.*;
import arc.graphics.*;
import mindustry.graphics.*; 
import mindustry.ctype.*;
import mindustry.type.*;
import mindustry.*;

public class BStatusEffects implements ContentList{
    public static StatusEffect
    
    severed;
    
    @Override
    public void load(){
        severed = new StatusEffect("severed"){{
            color = Pal.health;
            speedMultiplier = 1.3f;
            healthMultiplier = 0.6f;
            damage = 0.3f;
        }};
    };
}