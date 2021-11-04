package example.content;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.struct.*;
import arc.util.*;
import mindustry.entities.*;
import mindustry.entities.Effect.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static mindustry.Vars.*;

public class BFx {
  public static final Effect
  
  barrierRepulse = new Effect(100f, e -> {
    float ihiAlpha = Interp.pow5Out.apply(e.fout());
    float radius = e.finpow() * 8f + 2f;
    color(Pal.lancerLaser, Color.white, e.fin());
    alpha(ihiAlpha);
    stroke(4f * e.fout() + 1f);
    Lines.circle(e.x, e.y, radius);
    alpha(ihiAlpha - 0.2f);
    Fill.circle(e.x, e.y, radius);
    reset();
  });
}