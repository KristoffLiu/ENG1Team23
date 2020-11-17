package com.engoneassessment.game.utils;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.engoneassessment.game.GameEntry;

public class BlurShaderHelper {
    public  final static float MAX_BLUR = 5.0F;     // 最大的模糊系数
    private static SpriteBatch blurBatch;
    private static String vertexShader;            // 定点
    private static String fragmentShader;            // 片段
    private static ShaderProgram shader;            // 自定义着色器
    private static FrameBuffer frameBufferA;        // 纹理缓存A，实际上就是用来存放上一次纹理缓存的拷贝
    private static FrameBuffer frameBufferB;        // 纹理缓存B
    private static float radius = 0.0F;                // 初始的模糊系数
    private static int fbo_size = 1024;    // 纹理缓存大小
    private static float  blur = 0.0F;        // 模糊系数
    private static  float time = 0;            // 偏移的时间
    private static float xOffset = 0.8F;    // x轴偏移，水平渲染
    private static float yOffset = 0.8F;    // y轴偏移，垂直渲染

    static {
        //定点
        vertexShader = "uniform mat4  u_projTrans;\n "
                + "attribute vec2 a_position;\n "
                + "attribute vec2 a_texCoord0;\n"
                + "attribute vec4 a_color;\n"
                + "varying vec4 vColor;\n"
                + "varying vec2 vTexCoord;\n"
                + "void main() {\n"
                + "    vColor = a_color;\n"
                + "    vTexCoord = a_texCoord0;\n"
                + "    gl_Position = u_projTrans * vec4(a_position, 0.0, 1.0);\n"
                + "}";

        fragmentShader = "varying vec4 vColor;\n"
                + "varying vec2 vTexCoord;\n"
                +"uniform sampler2D u_texture;\n"
                + "uniform float resolution;\n"
                + "uniform float radius;\n"
                + "uniform vec2 dir;\n"
                + "void main() {\n"
                + "    vec4 sum = vec4(0.0);\n"
                + "    vec2 tc = vTexCoord;\n"
                + "    float blur = radius/resolution; \n"
                + "    float hstep = dir.x;\n"
                + "    float vstep = dir.y;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x - 4.0*blur*hstep, tc.y - 4.0*blur*vstep)) * 0.0162162162;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x - 3.0*blur*hstep, tc.y - 3.0*blur*vstep)) * 0.0540540541;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x - 2.0*blur*hstep, tc.y - 2.0*blur*vstep)) * 0.1216216216;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x - 1.0*blur*hstep, tc.y - 1.0*blur*vstep)) * 0.1945945946;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x, tc.y)) * 0.2270270270;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x + 1.0*blur*hstep, tc.y + 1.0*blur*vstep)) * 0.1945945946;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x + 2.0*blur*hstep, tc.y + 2.0*blur*vstep)) * 0.1216216216;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x + 3.0*blur*hstep, tc.y + 3.0*blur*vstep)) * 0.0540540541;\n"
                + "    sum += texture2D(u_texture, vec2(tc.x + 4.0*blur*hstep, tc.y + 4.0*blur*vstep)) * 0.0162162162;\n"
                + "    gl_FragColor = vColor * vec4(sum.rgb, 1.0);\n" + "}";

//        vertexShader = Gdx.files.internal("blur/vertex.vert").readString(); // 读取定点着色
//        fragmentShader = Gdx.files.internal("blur/fragment.frag").readString(); // 读取片段着色
        frameBufferA = new FrameBuffer(Pixmap.Format.RGBA8888, 800, 480, false);
        frameBufferB = new FrameBuffer(Pixmap.Format.RGBA8888, 800, 480, false);
        shader = new ShaderProgram(vertexShader, fragmentShader);
        blurBatch = new SpriteBatch();
        //blurBatch.setProjectionMatrix(GameEntry.current.stage.getCamera().projection);
    }

    public static void blur(Stage stage) {

        if (shader != null && shader.isCompiled()) {
            time += Gdx.graphics.getDeltaTime();
            Gdx.gl.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            blur = time < MAX_BLUR ? time : MAX_BLUR;
            blurRander(stage);
            horizontalBlur();
            verticalBlur();
        }

    }

    private static void blurRander(Stage stage){
        frameBufferA.begin();
        blurBatch.begin();
        shader.begin();
        shader.setUniformf("dir", 0f, 0f);
        shader.setUniformf("radius", radius );
        shader.setUniformf("resolution", fbo_size);
        blurBatch.setShader(shader);
        stage.draw();
        stage.getRoot().draw(blurBatch, 1);
        blurBatch.flush();
        frameBufferA.end();
    }

    private static void horizontalBlur()   {
        blurBatch.setShader(shader);
        shader.setUniformf("dir", xOffset, 0f);
        shader.setUniformf("radius",blur );

        frameBufferB.begin();

        blurBatch.draw(frameBufferA.getColorBufferTexture(), 0, 0);//绘制纹理缓存A
        blurBatch.flush();
        frameBufferB.end();
    }

    private static void verticalBlur()   {
        shader.setUniformf("dir", 0f, yOffset);
        shader.setUniformf("radius",blur );

        blurBatch.draw(frameBufferB.getColorBufferTexture(), 0, 0);// 绘制纹理缓存B
        blurBatch.flush();
        blurBatch.end();
        shader.end();

    }

    public static void dispose() {
        blurBatch.dispose();
        shader.dispose();
    }
}