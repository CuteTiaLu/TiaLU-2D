/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package TiaLU.Render.GraphicsRenderingModule;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import static TiaLU.Render.Camera.*;

public class Render extends SpriteBatch {

    //主要渲染Test0.1~0.9
    //Main rendering

    public void draw(Texture texture, double x, double y, double width, double height) {
        double T = PixelSize * camera.Zoom;
        float
                TX = (float) ((x - camera.Position.x) * T),
                TY = (float) ((y - camera.Position.y) * T),
                TWidth = (float) (width * T),
                THeight = (float) (height * T);
        if ((TX > camera.ViewportWidth * T || TX + TWidth < -camera.ViewportWidth * T || TY > camera.ViewportHeight * T || TY + THeight < -camera.ViewportHeight * T)) return;
        super.draw(texture, TX, TY, TWidth, THeight);
    }

    public void draw(Texture texture, double x, double y, float rotate) {
        draw(texture, x, y, texture.getWidth(), texture.getHeight(), rotate);
    }

    public void draw(Texture texture, double x, double y, double width, double height, float rotate) {
        draw(texture, x, y, width, height, width * 0.5, height * 0.5, rotate);
    }

    public void draw(Texture texture, double x, double y, double width, double height, double originX, double originY, float rotate) {
        double T = PixelSize * camera.Zoom;
        float
                TX = (float) ((x - camera.Position.x) * T),
                TY = (float) ((y - camera.Position.y) * T),
                TWidth = (float) (width * T),
                THeight = (float) (height * T),
                TOriginX = (float) (originX * T),
                TOriginY = (float) (originY * T);
        if ((TX > camera.ViewportWidth * T || TX + TWidth < -camera.ViewportWidth * T || TY > camera.ViewportHeight * T || TY + THeight < -camera.ViewportHeight * T)) return;
        float TRotate = rotate - camera.Position.rotate;
        super.draw(texture, TX, TY, TOriginX, TOriginY, TWidth, THeight, 1, 1, TRotate, 0, 0, texture.getWidth(),  texture.getHeight(), false, false);
    }

    //public void draw(Texture texture, double x, double y, double width, double height) {
    //    AddRend(texture, x, y, width, height, 0f, 1f, 1f, 1f, 1f, 0f, 0f, 0f);
    //}

    //public void draw(Texture texture, double x, double y, double width, double height, float rotate) {
    //    AddRend(texture, x, y, width, height, rotate, width * 0.5, height * 0.5, 0f, 1f, 1f, 1f, 1f, 0f, 0f, 0f);
    //}

    //a0.1

    public Render() {
        Gdx.gl.glLineWidth(1);
    }

    protected double PixelSize = 120;

    /*
    protected int Bit;
    protected float[] MatrixData;
    protected Texture[] TextureData;
    protected boolean start = false;
    protected int Size;
    protected int SelectShader = 0;

    protected Mesh mesh;
    protected Shader[] shader;
    protected Shader DefaultShader;

    protected float[] Shape;
    protected int[] ShapeIndex;

    protected boolean BlendingDisabled = false;

    protected int blendSrcFunc = GL20.GL_SRC_ALPHA;
    protected int blendDstFunc = GL20.GL_ONE_MINUS_SRC_ALPHA;
    protected int blendSrcFuncAlpha = GL20.GL_SRC_ALPHA;
    protected int blendDstFuncAlpha = GL20.GL_ONE_MINUS_SRC_ALPHA;
    protected Matrix4 ProjectionMatrix = new Matrix4();

    public Render() {
        this(16384, 12, 800f);
    }

    public Render(int size, int amount, float PixelSize) {
        Size = Math.min(size, 16384);
        MatrixData = new float[Size * 16];
        TextureData = new Texture[Size];

        this.PixelSize = PixelSize;

        ProjectionMatrix.setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Mesh.VertexDataType vertexDataType = (Gdx.gl30 != null) ? Mesh.VertexDataType.VertexBufferObjectWithVAO : Mesh.VertexDataType.VertexArray;

        mesh = new Mesh(vertexDataType, false, Size * 4, Size * 6,
                new VertexAttribute(0, 2, "V_Position"),
                new VertexAttribute(1, 2, "V_TexCoords"));

        short[] indices = new short[Size * 6];
        for (int i = 0; i < Size; i++) {
            int base = i * 4;
            indices[i * 6] = (short)(base);
            indices[i * 6 + 1] = (short)(base + 1);
            indices[i * 6 + 2] = (short)(base + 2);
            indices[i * 6 + 3] = (short)(base + 2);
            indices[i * 6 + 4] = (short)(base + 3);
            indices[i * 6 + 5] = (short)(base);
        }
        mesh.setIndices(indices);

        Bit = -1;
        shader = new Shader[amount];
        DefaultShader = new Shader(
            """
            attribute vec2 V_Position;
            attribute vec2 V_TexCoords;
            uniform mat4 ProjectionMatrix;
            varying vec2 F_TexCoords;
            
            void main() {
                F_TexCoords = V_TexCoords;
                gl_Position = vec4( V_Position.x, V_Position.y, 0.0, 1.0 );
            }
            """,
            """
            precision mediump float;
            uniform sampler2D F_Texture;
            varying vec2 F_TexCoords;
            void main() {
                gl_FragColor = texture2D(F_Texture, F_TexCoords);
            }
            """
        );

        DefaultShader.CompileShader();
    }

    protected void AddRend(Texture texture, double x, double y, double width, double height, float rotate, double originX, double originY, float... uv) {
        if (Bit >= Size) return;
        float
                TX = (float) (x - camera.CameraPosition.x),
                TY = (float) (y - camera.CameraPosition.y),
                TWidth = (float) (width),
                THeight = (float) (height);
        if ((TX > camera.ViewportWidth * PixelSize || TX + TWidth < -camera.ViewportWidth * PixelSize || TY > camera.ViewportHeight * PixelSize || TY + THeight < -camera.ViewportHeight * PixelSize)) return;
        float TRotate = rotate - camera.CameraPosition.rotate;

        float
                TOriginX = (float) originX,
                TOriginY = (float) originY;

        float
                fx0 = -TOriginX,
                fy0 = -TOriginY,
                fx1 = TWidth - TOriginX,
                fy1 = THeight - TOriginY;

        float
                x0 = fx0,
                y0 = fy0,
                x1 = fx0,
                y1 = fy1,
                x2 = fx1,
                y2 = fy1,
                x3 = fx1,
                y3 = fy0;

        if (TRotate != 0) {

            float cos = MathUtils.cosDeg(TRotate);
            float sin = MathUtils.sinDeg(TRotate);

            x0 = cos * fx0 - sin * fy0;
            y0 = sin * fx0 + cos * fy0;

            x1 = cos * fx0 - sin * fy1;
            y1 = sin * fx0 + cos * fy1;

            x2 = cos * fx1 - sin * fy1;
            y2 = sin * fx1 + cos * fy1;

            x3 = x0 + (x2 - x1);
            y3 = y2 - (y1 - y0);
        }

        MatrixData[Bit * 16] = camera.VertexTransX(x0 + TX);
        MatrixData[Bit * 16 + 1] = camera.VertexTransY(y0 + TY);
        MatrixData[Bit * 16 + 2] = camera.VertexTransX(x1 + TX);
        MatrixData[Bit * 16 + 3] = camera.VertexTransY(y1 + TY);
        MatrixData[Bit * 16 + 4] = camera.VertexTransX(x2 + TX);
        MatrixData[Bit * 16 + 5] = camera.VertexTransY(y2 + TY);
        MatrixData[Bit * 16 + 6] = camera.VertexTransX(x3 + TX);
        MatrixData[Bit * 16 + 7] = camera.VertexTransY(y3 + TY);

        TextureData[Bit] = texture;

        MatrixData[Bit * 16 + 8] = uv[0];
        MatrixData[Bit * 16 + 9] = uv[1];
        MatrixData[Bit * 16 + 10] = uv[2];
        MatrixData[Bit * 16 + 11] = uv[3];
        MatrixData[Bit * 16 + 12] = uv[4];
        MatrixData[Bit * 16 + 13] = uv[5];
        MatrixData[Bit * 16 + 14] = uv[6];
        MatrixData[Bit * 16 + 15] = uv[7];

        Bit++;
    }

    protected void AddRend(Texture texture, double x, double y, double width, double height, float... uv) {
        if (Bit >= Size) return;
        float
                TX = (float) ((x - camera.CameraPosition.x)),
                TY = (float) ((y - camera.CameraPosition.y)),
                TWidth = (float) (width),
                THeight = (float) (height);
        if ((TX > camera.ViewportWidth * PixelSize || TX + TWidth < -camera.ViewportWidth * PixelSize || TY > camera.ViewportHeight * PixelSize || TY + THeight < -camera.ViewportHeight * PixelSize)) return;

        MatrixData[Bit * 16] = camera.VertexTransX(TX);
        MatrixData[Bit * 16 + 1] = camera.VertexTransY(THeight);
        MatrixData[Bit * 16 + 2] = camera.VertexTransX(TWidth);
        MatrixData[Bit * 16 + 3] = camera.VertexTransY(THeight);
        MatrixData[Bit * 16 + 4] = camera.VertexTransX(TWidth);
        MatrixData[Bit * 16 + 5] = camera.VertexTransY(TY);
        MatrixData[Bit * 16 + 6] = camera.VertexTransX(TX);
        MatrixData[Bit * 16 + 7] = camera.VertexTransY(TY);

        TextureData[Bit] = texture;

        MatrixData[Bit * 16 + 8] = uv[0];
        MatrixData[Bit * 16 + 9] = uv[1];
        MatrixData[Bit * 16 + 10] = uv[2];
        MatrixData[Bit * 16 + 11] = uv[3];
        MatrixData[Bit * 16 + 12] = uv[4];
        MatrixData[Bit * 16 + 13] = uv[5];
        MatrixData[Bit * 16 + 14] = uv[6];
        MatrixData[Bit * 16 + 15] = uv[7];

        Bit++;
    }

    public void Start() {
        if (start) throw new IllegalStateException("Start called multiple times without End. \n Start 被两次调用但没有调用 End。");
        MatrixData = new float[Size * 16];
        TextureData = new Texture[Size];
        Bit = 0;
        start = true;
    }

    public void End() {
        if (!start) throw new IllegalStateException("End called without Start \n 调用 End 前未调用 Start");
        if (Bit > 0) Flush();
        MatrixData = null;
        TextureData = null;
        start = false;
    }

    protected void Flush () {
        int SpritesInBatch = Bit;
        int Count = SpritesInBatch * 6;   // 每个精灵 6 个索引 (2 个三角形)

        Mesh mesh = this.mesh;
        mesh.setVertices(MatrixData, 0, Bit * 16);
        Buffer indicesBuffer = mesh.getIndicesBuffer(true);
        indicesBuffer.position(0);
        indicesBuffer.limit(Count);

        ShaderProgram shaderprogram = (shader != null && shader[SelectShader] != null && shader[SelectShader].Shader != null) ? shader[SelectShader].Shader : DefaultShader.Shader;

        if (BlendingDisabled) {
            Gdx.gl.glDisable(GL20.GL_BLEND);
        } else {
            Gdx.gl.glEnable(GL20.GL_BLEND);
            if (blendSrcFunc != -1) {
                Gdx.gl.glBlendFuncSeparate(blendSrcFunc, blendDstFunc, blendSrcFuncAlpha, blendDstFuncAlpha);
            }
        }

        if (shaderprogram != null) {
            //shaderprogram.setUniformMatrix("ProjectionMatrix", ProjectionMatrix);
            if (TextureData[0] != null) {
                TextureData[0].bind(0);
            }
            shaderprogram.setUniformi("F_Texture", 0);
            mesh.render(shaderprogram, GL20.GL_TRIANGLES, 0, Count);
        }
    }

     */

    //基础

    public double GetPixelSize() {
        return PixelSize;
    }
}