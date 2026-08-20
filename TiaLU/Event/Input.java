package TiaLU.Event;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;

public class Input {
    public static HashMap<String, Integer> Keystroke = new HashMap<>();

    public static void SetKeystroke(HashMap<String, Integer> Keystroke) {
        Input.Keystroke = Keystroke;
    }

    public static boolean isPressed(String action) {
        int Key = Keystroke.get(action);
        return Gdx.input.isKeyPressed(Key);
    }
}
