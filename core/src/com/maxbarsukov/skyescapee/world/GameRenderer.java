package com.maxbarsukov.skyescapee.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.maxbarsukov.skyescapee.entities.Ground;
import com.maxbarsukov.skyescapee.entities.Player;
import com.maxbarsukov.skyescapee.entities.Tube;
import com.maxbarsukov.skyescapee.helpers.AssetLoader;
import com.maxbarsukov.skyescapee.helpers.ScrollHandler;
import com.maxbarsukov.skyescapee.screens.GameScreen;
import com.maxbarsukov.skyescapee.world.interfaces.GameEntities;
import com.maxbarsukov.skyescapee.world.interfaces.Status;

import static com.maxbarsukov.skyescapee.Main.SCORE_POSITION;
import static com.maxbarsukov.skyescapee.entities.Tube.TUBE_GAP;


public class GameRenderer implements Disposable {

    private GameEntities gameEntities;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    // Game Objects
    private Player player;
    private Tube tube1;
    private Tube tube2;
    private Tube tube3;
    private Ground frontGround;
    private Ground backGround;

    // Game Assets
    private Texture groundTxtr;
    private Texture backgroundDayTxtr;
    private Texture tubeTxtr;
    private Texture startTxtr;
    private Texture gameover;
    private Animation<TextureRegion> playerAnim;

    public GameRenderer(GameEntities gameEntities) {
        this.gameEntities = gameEntities;

        OrthographicCamera camera = new OrthographicCamera();
        camera.setToOrtho(false, GameScreen.WIDTH, GameScreen.HEIGHT);

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(camera.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);

        initGameObjects();
        initAssets();
    }

    private void initGameObjects() {
        player = gameEntities.getPlayer();
        ScrollHandler scroller = gameEntities.getScroller();
        frontGround = scroller.getFrontGround();
        backGround = scroller.getBackGround();
        tube1 = scroller.getTube1();
        tube2 = scroller.getTube2();
        tube3 = scroller.getTube3();
    }

    private void initAssets() {
        backgroundDayTxtr = AssetLoader.BACKGROUND_DAY;
        groundTxtr = AssetLoader.GROUND;
        tubeTxtr = AssetLoader.TUBE;
        playerAnim = AssetLoader.PLAYER_ANIMATION;
        startTxtr = AssetLoader.START;
        gameover = AssetLoader.GAMEOVER;
    }

    public void render(float runTime) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Begin SpriteBatch
        spriteBatch.begin();

        spriteBatch.disableBlending();
        drawBackground();

        spriteBatch.enableBlending();
        drawTubes();

        spriteBatch.disableBlending();
        drawGround();

        spriteBatch.enableBlending();
        drawPlayer(runTime);

        drawMessage();

        // End SpriteBatch
        spriteBatch.end();
    }

    private void drawBackground() {
        spriteBatch.draw(backgroundDayTxtr, 0, 0, GameScreen.WIDTH, GameScreen.HEIGHT);
    }

    private void drawPlayer(float runTime) {

        if (player.doFlap()) {
            spriteBatch.draw(playerAnim.getKeyFrame(runTime), player.getX(), player.getY(), player.getWidth() / 2.0f, player.getHeight() / 2.0f, player.getWidth(),
                    player.getHeight(), 1, 1, player.getRotation());
        } else {
            spriteBatch.draw(playerAnim.getKeyFrames()[1], player.getX(), player.getY(), player.getWidth() / 2.0f, player.getHeight() / 2.0f, player.getWidth(),
                    player.getHeight(), 1, 1, player.getRotation());
        }
    }

    private void drawGround() {
        spriteBatch.draw(groundTxtr, frontGround.getX(), frontGround.getY(), frontGround.getWidth(), frontGround.getHeight());
        spriteBatch.draw(groundTxtr, backGround.getX(), backGround.getY(), backGround.getWidth(), backGround.getHeight());
    }

    private void drawTubes() {
        // Draw Tube 1
        spriteBatch.draw(tubeTxtr, tube1.getX(), tube1.getY(), tube1.getWidth(), tube1.getHeight());
        spriteBatch.draw(tubeTxtr, tube1.getX(), tube1.getY() + tube1.getHeight() * 2 + TUBE_GAP, tube1.getWidth(), -tube1.getHeight());

        // Draw Tube 2
        spriteBatch.draw(tubeTxtr, tube2.getX(), tube2.getY(), tube2.getWidth(), tube2.getHeight());
        spriteBatch.draw(tubeTxtr, tube2.getX(), tube2.getY() + tube2.getHeight() * 2 + TUBE_GAP, tube2.getWidth(), -tube2.getHeight());

        // Draw Tube 3
        spriteBatch.draw(tubeTxtr, tube3.getX(), tube3.getY(), tube3.getWidth(), tube3.getHeight());
        spriteBatch.draw(tubeTxtr, tube3.getX(), tube3.getY() + tube3.getHeight() * 2 + TUBE_GAP, tube3.getWidth(), -tube3.getHeight());
    }

    private void drawMessage() {

        if (((Status) gameEntities).isReady()) {
            // Draw start
            spriteBatch.draw(startTxtr, GameScreen.WIDTH / 2 - startTxtr.getWidth() / 2, GameScreen.HEIGHT / 2 - startTxtr.getHeight() / 2 + 65,
                    startTxtr.getWidth(), startTxtr.getHeight());
        } else {
            // Draw end text
            if (((Status) gameEntities).isGameOver() || ((Status) gameEntities).isHighScore()) {
                spriteBatch.draw(gameover, GameScreen.WIDTH / 2 - startTxtr.getWidth() / 2, GameScreen.HEIGHT * 2 / 3 - startTxtr.getHeight() / 2 + 65,
                        gameover.getWidth(), gameover.getHeight());
            }

            if (((Status) gameEntities).isHighScore()) {
                AssetLoader.SHADOW.draw(spriteBatch, "New High Score :", GameScreen.WIDTH / 2 - 70, (int) (GameScreen.HEIGHT * 0.40) - 2);
                AssetLoader.FONT.draw(spriteBatch, "New High Score :", GameScreen.WIDTH / 2 - 71, (int) (GameScreen.HEIGHT * 0.40));
                AssetLoader.SHADOW.draw(spriteBatch, Integer.toString(AssetLoader.getHighScore()), GameScreen.WIDTH / 2, GameScreen.HEIGHT / 3);
                AssetLoader.FONT.draw(spriteBatch, Integer.toString(AssetLoader.getHighScore()), GameScreen.WIDTH / 2, GameScreen.HEIGHT / 3);
            }

            // Draw score
            String score = Integer.toString(gameEntities.getScore());
            AssetLoader.SHADOW.draw(spriteBatch, score, GameScreen.WIDTH - (SCORE_POSITION - 1) - (10 * score.length()),
                    GameScreen.HEIGHT - (SCORE_POSITION + 1));
            AssetLoader.FONT.draw(spriteBatch, score, GameScreen.WIDTH - SCORE_POSITION - (10 * score.length()), GameScreen.HEIGHT - SCORE_POSITION);
        }
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }
}
