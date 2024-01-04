package mino;

import java.awt.*;

public class Block extends Rectangle {
    public int x,y;
    public static final int SIZE=30;
    public Color blockcolor;

    public Block(Color c){
        this.blockcolor=c;
    }

    public void draw(Graphics2D g2){
        int margine=2;
        g2.setColor(blockcolor);
        g2.fillRect(x+margine,y+margine,SIZE-(margine*2), SIZE-(margine*2));
    }
}
