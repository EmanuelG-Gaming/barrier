package example;

import arc.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

public class ExampleJavaMod extends Mod{

    public ExampleJavaMod(){
        Log.info("Loaded ExampleJavaMod constructor.");

        //listen for game load event
        Events.on(ClientLoadEvent.class, e -> {
            //show dialog upon startup
            boolean show = true;
            Time.runTask(10f, () -> {
                BaseDialog dialog = new BaseDialog("Yes");
                dialog.cont.add("behold").row();
                //mod sprites are prefixed with the mod name (this mod is called 'example-java-mod' in its config)
                dialog.cont.image(Core.atlas.find("error")).pad(20f).row(); // haha no
                dialog.cont.button("Hide this and never show it again", () -> {
                  dialog::hide;
                  show = false;
                }).size(100f, 50f);
                if (show) dialog.show();
            });
        });
    }

    @Override
    public void loadContent(){
      Log.info("Loading some example content.");
    }

}
