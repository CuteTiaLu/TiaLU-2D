attribute vec2 V_Position;
attribute vec2 V_TexCoords;
uniform mat4 ProjectionMatrix;
varying vec2 F_TexCoords;

void main() {
    F_TexCoords = V_TexCoords;
    gl_Position = vec4( V_Position.x, V_Position.y, 0.0, 1.0 );
}