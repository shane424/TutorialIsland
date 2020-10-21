package Utils;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;
import org.osbot.rs07.script.Script;

import javax.jws.soap.SOAPBinding;

public class Mining {
    Script script = null;
    public Mining(Script script) {
        this.script = script;
    }

    public void checkIfTooFar() throws InterruptedException {
        if(script.widgets.isVisible(162)){
            script.widgets.get(162,33).interact();
            script.sleep(script.random(400,700));
        }
    }

    public void waiting() throws InterruptedException {
        while (script.myPlayer().isAnimating() || script.myPlayer().isMoving()) {
            script.sleep(script.random(100, 400));
        }
    }

    public void execute()  throws InterruptedException {
        checkIfTooFar();
        switch (script.configs.get(281)) {
            case 260:
                script.walking.walk(new Position(3082, 9508, 0));
                waiting();
                script.npcs.closest("Mining Instructor").interact("Talk-to");
                waiting();
                script.keyboard.typeKey(' ', 0, script.random(1200,1600), false);
                break;
            case 270:
                script.objects.closest(10080).interact("Prospect");
                waiting();
                script.sleep(script.random(1300,1700));
                break;
            case 280:
                script.objects.closest(10079).interact("Prospect");
                waiting();
                script.sleep(script.random(1300,1700));
                break;
            case 290:
                script.npcs.closest("Mining Instructor").interact("Talk-to");
                waiting();
                script.keyboard.typeKey(' ', 0, script.random(1200,1600), false);
                break;
            case 300:
                script.objects.closest(10080).interact("Mine");
                waiting();
                break;
            case 310:
                script.objects.closest(10079).interact("Mine");
                waiting();
                break;
            //case 311:
                //mine Tin..?
            case 320:
                script.inventory.interact("Use", "Tin ore");
                script.sleep(script.random(600,900));
                script.objects.closest("Furnace").interact("Use");
                script.sleep(script.random(700,900));
                break;
            case 330:
                script.keyboard.pressKey(32);
                script.sleep(script.random(400,600));
                script.keyboard.releaseKey(32);
                script.npcs.closest("Mining Instructor").interact("Talk-to");
                waiting();
                script.keyboard.typeKey(' ', 0, script.random(800,1000), false);
                break;
            case 340:
                if(script.inventory.isItemSelected()){
                    script.objects.closest("Anvil").interact("Use");
                    waiting();
                } else{
                    script.inventory.interact("Use", "Bronze bar");
                    script.sleep(script.random(500,700));
                }
                break;
            case 350:
                if(script.widgets.get(312, 1, 0) != null && script.widgets.get(312, 1, 0).isVisible()) {
                    script.widgets.get(312, 2, 2).interact("Smith 1");
                } else {
                    if(!script.myPlayer().isAnimating()) {
                        script.inventory.interact("Use", "Bronze bar");
                        script.sleep(script.random(500,700));
                        if(script.inventory.isItemSelected()){
                            script.objects.closest("Anvil").interact("Use");
                            waiting();
                        } else{
                            script.inventory.interact("Use", "Bronze bar");
                            script.sleep(script.random(500,700));
                        }
                    }
                }
                script.sleep(script.random(600,900));
                break;
            case 360:
                script.walking.walk(new Position(3090, 9504, 0));
                waiting();
                script.objects.closest("Gate").interact("Open");
                break;
        }

    }
}
