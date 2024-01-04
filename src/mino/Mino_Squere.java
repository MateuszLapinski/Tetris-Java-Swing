package mino;

import java.awt.*;

public class Mino_Squere extends Mino{

    public Mino_Squere() {
        create(Color.YELLOW);
    }

    @Override
    public void setPosition(int x, int y) {
        blockTable[0].x=x;
        blockTable[0].y=y;

        blockTable[1].x=blockTable[0].x;
        blockTable[1].y=blockTable[0].y+ Block.SIZE;

        blockTable[2].x=blockTable[0].x+ Block.SIZE;
        blockTable[2].y=blockTable[0].y;

        blockTable[3].x=blockTable[0].x+ Block.SIZE;
        blockTable[3].y=blockTable[0].y+ Block.SIZE;
    }
    @Override
    public void getDirection1(){
    }
    public void getDirection2() {
    }

    public void getDirection3(){
    }
    public void getDirection4(){
    }
}
