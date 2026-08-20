package TiaLU.window;

import TiaLU.API.SceAPI;
import TiaLU.API.TiaAPI;
import TiaLU.Event.InputEvent;
import TiaLU.Render.GraphicsRenderingModule.Render;
import TiaLU.Storage.ThreadMethodBody;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static TiaLU.API.TiaAPI.Rend;
import static TiaLU.Render.Camera.camera;
import static java.lang.Thread.sleep;

public abstract class Game implements ApplicationListener {

    private long currentTime = System.nanoTime();
    private long lastTime = System.nanoTime();
    private double delta;

    private ExecutorService GameThread = Executors.newCachedThreadPool();

    public Game() {}

    @Override
    public void render() {
        currentTime = System.nanoTime();
        delta = (currentTime - lastTime) * 0.000000001;
        lastTime = currentTime;
        Camera();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Rend.begin();
        Run(delta);
        Rend.end();
    }

    @Override
    public void dispose() {
        GameThread.shutdownNow();
        cease();
    }

    public void create() {
        Gdx.input.setInputProcessor(new InputEvent());
        camera.ViewportWidth = Gdx.graphics.getWidth() * 0.5;
        camera.ViewportHeight = Gdx.graphics.getHeight() * 0.5;
        SceneCreation();
        init();
        SceAPI.sceneInit();
        ThreadStart();
    }

    @Override
    public void resize(int width, int height) {
        camera.ViewportWidth = width * 0.5;
        camera.ViewportHeight = height * 0.5;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    protected void WithLoop(String name, int FPS, ThreadMethodBody run) {
        int Fps = FPS(FPS);
        GameThread.submit(() -> {
            Thread.currentThread().setName(name);
            long lastTime = System.nanoTime();
            long currentTime;
            double delta;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    currentTime = System.nanoTime();
                    delta = (currentTime - lastTime) * 0.000000001;
                    lastTime = currentTime;
                    run.run(delta);
                    sleep(Fps);
                } catch (InterruptedException e) {
                    dispose();
                    throw new RuntimeException(e);
                }
            }
        });
    }

    protected int FPS(int FPS) {
        return 1000 / FPS;
    }

    protected abstract void SceneCreation();

    protected abstract void init();

    protected abstract void ThreadStart();

    protected abstract void cease();

    protected abstract void Camera();

    protected abstract void Run(double delta);

}
