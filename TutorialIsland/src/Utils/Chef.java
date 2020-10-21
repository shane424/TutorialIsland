package Utils;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;

public class Chef {
    public Script script = null;

    public Chef(Script script){
        this.script = script;
    }

    public Area nextToDoor = new Area(3079, 3085, 3081, 3083);

//    public void spaceBar() throws InterruptedException {
//        script.keyboard.pressKey(32);
//        script.sleep(script.random(1500, 2500));
//        script.keyboard.releaseKey(32);
//    }

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

    private void performRandomEmote() throws InterruptedException {
        int id = script.random(1,22);

        if(script.getTabs().getOpen().equals(Tab.EMOTES)) {
            if(script.widgets.get(216, 1, id) != null) {
                script.widgets.get(216, 1, id).interact();
            }
        } else {
            script.getTabs().open(Tab.EMOTES);
        }
        script.sleep(script.random(500,900));
    }

    public void execute() throws InterruptedException {
        checkIfTooFar();
        switch (script.configs.get(281)) {
            case 130:
                script.walking.walk(nextToDoor);
                waiting();
                script.objects.closest("Door").interact("Open");
                break;
            case 140:
                script.npcs.closest("Master Chef").interact("Talk-to");
                waiting();
                script.keyboard.typeKey(' ', 0, script.random(1500,1800), false);
                //script.sleep(script.random(800,1000));
                break;
            case 150:
                script.inventory.interact("Use", "Pot of Flour");
                if(script.inventory.isItemSelected()){
                    script.inventory.interact("Use", "Bucket of water");
                }
                else{
                    script.inventory.interact("Use", "Pot of Flour");
                    script.sleep(script.random(500,900));
                    script.inventory.interact("Use", "Bucket of water");
                }
                break;
            case 160:
                script.inventory.interact("Use", "Bread dough");
                script.sleep(script.random(500,800));
                if(script.inventory.isItemSelected()){
                    script.objects.closest("Range").interact("Use");
                }else{
                    script.inventory.interact("Use", "Bread dough");
                    script.sleep(script.random(500,900));
                    script.objects.closest("Range").interact("Use");
                    waiting();
                }
                break;
            case 170:
                script.getTabs().open(Tab.MUSIC);
                script.sleep(script.random(500,900));
                break;
            case 180:
                script.walking.walk(new Area(3073, 3091, 3073, 3089));
                waiting();
                script.objects.closest("Door").interact("Open");
                waiting();
                break;
            case 183:
                script.getTabs().open(Tab.EMOTES);
                script.sleep(script.random(500,900));
                break;
            case 187:
                performRandomEmote();
                script.sleep(script.random(500,900));
                break;
            case 190:
                script.getTabs().open(Tab.SETTINGS);
                script.sleep(script.random(500,900));
                break;
            case 200:
                script.widgets.get(261, 68).interact();
                script.sleep(script.random(500,900));
                break;
        }
    }
}
