package TiaLU.Event;

import com.badlogic.gdx.InputProcessor;

import static com.badlogic.gdx.Input.Keys.BACKSPACE;

public class InputEvent implements InputProcessor {

    private static char Pressed;
    private static boolean B67;

    public static char GetPressed() {
        return Pressed;
    }

    public static String EnterText(String text, char Char) {
        return (Char != '\u0000' && !B67) ? text + Char : B67 && !text.isEmpty() ? text.substring(0, text.length() - 1) : text;
    }

    public static String EnterText(String text) {
        return (GetPressed() != '\u0000' && !B67) ? text + GetPressed() : B67 && !text.isEmpty() ? text.substring(0, text.length() - 1) : text;
    }

    @Override
    public boolean keyDown(int keycode) {
        B67 = keycode == BACKSPACE;
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        Pressed = '\u0000';
        B67 = false;
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        Pressed = character;
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
