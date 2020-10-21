package Utils;


import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.model.Entity;
import org.osbot.rs07.api.ui.Tab;
import org.osbot.rs07.script.Script;

public class SurvivalExpert {
    Script script = null;
    Area fireArea = new Area(3100, 3098, 3107, 3095);

    public SurvivalExpert(Script script) {
        this.script = script;
    }

//    public void spaceBar() throws InterruptedException {
//        script.keyboard.pressKey(32);
//        script.sleep(script.random(500, 1500));
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

    private void cutTree() throws InterruptedException {
        script.objects.closest("Tree").interact("Chop Down");
        waiting();
    }

    private void catchShrimp() throws InterruptedException {
        script.npcs.closest("Fishing spot").interact("Net");
        while(!script.inventory.contains("Raw shrimps"))
            script.sleep(script.random(300,700));
    }

    private void makeFire() throws InterruptedException {
        if(fireArea.contains(script.myPlayer())) {
            if (script.inventory.contains("Logs")) {
                script.inventory.interact("Use", "Tinderbox");
                script.sleep(script.random(200, 400));
                script.inventory.interact("Use", "Logs");
                script.sleep(script.random(400,600));
                waiting();
            } else {
                cutTree();
                makeFire();
            }
        }
        else{
            script.walking.walk(fireArea.getRandomPosition());
            waiting();
            makeFire();
        }
    }

    private void cookShrimp() throws InterruptedException {
        if (!script.inventory.contains("Raw shrimps")) {
            catchShrimp();
        }
        if(script.inventory.contains("Burnt shrimps")){
            script.inventory.drop("Burnt shrimps");
        }
        if (script.objects.closest("Fire") != null) {
            script.inventory.interact("Use", "Raw shrimps");
            script.objects.closest("Fire").interact("Use");
            waiting();
        } else {
            if (script.inventory.contains("Logs")) {
                makeFire();
                cookShrimp();
            } else {
                cutTree();
                makeFire();
                cookShrimp();
            }
        }
    }

    public void execute() throws InterruptedException {
        checkIfTooFar();
        switch (script.configs.get(281)) {
            case 20:
                script.walking.walk(new Area(3099, 3100, 3105, 3094).getRandomPosition());
                script.sleep(script.random(300, 500));
                script.npcs.closest("Survival Expert").interact("Talk-to");
                waiting();
                script.keyboard.typeKey(' ', 0, script.random(800,1000), false);
                break;
            case 30:
                script.getTabs().open(Tab.INVENTORY);
                script.sleep(script.random(300, 500));
                break;
            case 40:
                script.objects.closest("Tree").interact("Chop Down");
                waiting();
                break;
            case 50:
                makeFire();
                break;
            case 60:
                script.getTabs().open(Tab.SKILLS);
                break;
            case 70:
                script.npcs.closest("Survival Expert").interact("Talk-to");
                waiting();
                script.keyboard.typeKey(' ', 0, script.random(800,1000), false);
                break;
            case 80:
                catchShrimp();
                break;
            case 90:
                cookShrimp();
                break;
            case 110:
                catchShrimp();
                cookShrimp();
                break;
        }
    }
}
