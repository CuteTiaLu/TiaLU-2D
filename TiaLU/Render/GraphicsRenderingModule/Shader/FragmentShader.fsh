precision mediump float;
uniform sampler2D F_Texture;
varying vec2 F_TexCords;
void main() {
    gl_FragColor = texture2D(F_Texture, F_TexCords);
}