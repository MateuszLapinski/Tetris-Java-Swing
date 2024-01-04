package mino;

import java.awt.*;

public class Mino_Bar extends Mino{

    public Mino_Bar() {
        create(Color.cyan);
    }

    @Override
    public void setPosition(int x, int y) {
        blockTable[0].x=x;
        blockTable[0].y=y;

        blockTable[1].x=blockTable[0].x- Block.SIZE;
        blockTable[1].y=blockTable[0].y;

        blockTable[2].x=blockTable[0].x+ Block.SIZE;;
        blockTable[2].y=blockTable[0].y;

        blockTable[3].x=blockTable[0].x+ Block.SIZE*2;
        blockTable[3].y=blockTable[0].y;
    }
    @Override
    public void getDirection1(){
        tempB[0].x= blockTable[0].x;
        tempB[0].y=blockTable[0].y;

        tempB[1].x=blockTable[0].x- Block.SIZE;
        tempB[1].y=blockTable[0].y;

        tempB[2].x=blockTable[0].x+ Block.SIZE;
        tempB[2].y=blockTable[0].y;

        tempB[3].x=blockTable[0].x+ Block.SIZE*2;
        tempB[3].y=blockTable[0].y;

        updatePosition(1);
    }
    public void getDirection2(){
        tempB[0].x= blockTable[0].x;
        tempB[0].y=blockTable[0].y;

        tempB[1].x=blockTable[0].x;
        tempB[1].y=blockTable[0].y- Block.SIZE;

        tempB[2].x=blockTable[0].x;
        tempB[2].y=blockTable[0].y+ Block.SIZE;

        tempB[3].x=blockTable[0].x;
        tempB[3].y=blockTable[0].y+ Block.SIZE*2;

        updatePosition(2);
    };
    public void getDirection3(){
        getDirection1();
    };
    public void getDirection4(){
        getDirection2();
    }
}
