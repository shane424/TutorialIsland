package Utils;

import org.osbot.rs07.api.ui.RS2Widget;
import org.osbot.rs07.script.Script;

import java.util.Random;

public class CreateChar{
    Script script = null;

    public CreateChar(Script script) {
        this.script = script;
    }

    private void interactionAmount(CharDesign button, RS2Widget interactAmount) throws InterruptedException {
        Random rn = new Random();
        int min = 0;
        int max = button.getPossibilities();
        int chooseInteractAmount = script.random(min, max);

        for (int i = 0; i < chooseInteractAmount; i++) {
            if (interactAmount != null) {
                interactAmount.interact();
            }
        }
        script.sleep(script.random(400, 700));
    }

    private void randomPickButtons(CharDesign button) throws InterruptedException {
        Random rn = new Random();
        int pickButtonMin = 0;
        int pickButtonMax = 1;
        int chooseButton = rn.nextInt(pickButtonMax - pickButtonMin + 1) + pickButtonMin;

        RS2Widget buttonLeft = script.widgets.get(button.getParent(), button.getChildLeft());
        RS2Widget buttonRight = script.widgets.get(button.getParent(), button.getChildRight());

        if (chooseButton == 0) {
            interactionAmount(button, buttonLeft);
        } else if (chooseButton == 1) {
            interactionAmount(button, buttonRight);
        }
    }

    public void execute() throws InterruptedException {


        int chooseGender = script.random(1, 5);

        RS2Widget genderButton = script.widgets.get(269, CharDesign.GENDER.getChildRight());

        if (chooseGender == 3 || chooseGender == 5) {
            if (genderButton != null) {
                genderButton.interact();
            }
        }

        randomPickButtons(CharDesign.HEAD_DESIGN);
        randomPickButtons(CharDesign.JAW_DESIGN);
        randomPickButtons(CharDesign.TORSO_DESIGN);
        randomPickButtons(CharDesign.ARMS_DESIGN);
        randomPickButtons(CharDesign.HANDS_DESIGN);
        randomPickButtons(CharDesign.LEGS_DESIGN);
        randomPickButtons(CharDesign.FEET_DESIGN);
        randomPickButtons(CharDesign.HAIR_COLOUR);
        randomPickButtons(CharDesign.TORSO_COLOUR);
        randomPickButtons(CharDesign.LEG_COLOUR);
        randomPickButtons(CharDesign.FEET_COLOUR);
        randomPickButtons(CharDesign.SKIN_COLOUR);

        RS2Widget makeChar = script.widgets.get(269, 100);
        makeChar.interact();
        script.sleep(script.random(1700, 3500));
    }

    public boolean validate() throws InterruptedException {
        return script.widgets.get(269, 97) != null && script.configs.get(406) == 0;
    }
}