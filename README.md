# Javitto TAS for Pokitto

git clone this repo into `FemtoIDE/projects` to work with it in FemtoIDE.

Following thread on the Pokitto forum [TAS mode for Javitto](https://talk.pokitto.com/t/tas-mode-for-javitto-development-thread)


# Patches to the femto java

[Sprite.java](https://github.com/felipemanga/FemtoIDE/blob/master/javacompiler/femto/Sprite.java)

add:

```Java
public void draw( TASMode screen) {
    draw(screen, this.x, this.y);
}
    
public void draw( TASMode screen, float x, float y ){
    updateTasAnimation();
    boolean mirror = (flags&2) != 0;
    boolean flip = (flags&4) != 0;

    if( (flags&1) == 0 ){
        x -= screen.cameraX;
        y -= screen.cameraY;
    }

    pointer frame;
    __inline_cpp__("
    const auto &f = *(const up_femto::uc_FrameRef*)getFrameDataForScreen(currentFrame, (up_femto::up_mode::uc_LowRes256Color*)nullptr);

    frame = f.frame;
    int frameWidth = ((char*)f.frame)[0];
    int frameHeight= ((char*)f.frame)[1];
    
    // Apply the offsets to the local x,y coordinates
    x = x.getInteger() + (mirror?this->width()-(frameWidth+(frameWidth&1)+f.offsetX):f.offsetX);
    y = y.getInteger() + (flip?this->height()-(frameHeight+f.offsetY):f.offsetY);
    
    ");
    screen.addSprite(frame, x, y, mirror, flip);
    return;
    getFrameDataForScreen(0, (LowRes256Color)null);
    width();
    height();
}

public void updateTasAnimation(){
    if( startFrame != endFrame ){

        uint now = System.currentTimeMillis();
        int delta = now - frameTime;
        pointer frameData;

        while( true ){
            int duration;
            __inline_cpp__("duration = ((up_femto::uc_FrameRef*)(getFrameDataForScreen(currentFrame, (up_femto::up_mode::uc_LowRes256Color*)nullptr)))->duration");
            if( duration >= delta )
                break;
            
            currentFrame++;
            delta -= duration;

            if( currentFrame > endFrame )
                currentFrame = startFrame;

            frameTime += duration;
        }
    }

}
    
```

`javacompiler/pokitto/begin.cpp` add:
```
extern "C" void flushLine16(uint16_t *line);
```

`javacompiler/pokitto/libs/SystemInit.s` add:
```
.global flushLine16

.func flushLine16
flushLine16:
LINE .req r0
X .req r1
LCD .req r2
TMP .req r3
WRBIT .req r4
CLR .req r5
OUT .req r6
        push {r4-r6, lr}
        ldr X, =-220*2
        subs LINE, X
        ldr WRBIT, =1<<12
        ldr CLR,   =252
        ldr LCD,   =0xA0002188

        ldrh OUT, [LINE, X]

1:
        lsls OUT, 3
        str OUT, [LCD]
        str WRBIT, [LCD, CLR]
        adds X, 2
        ldrh OUT, [LINE, X]
        str WRBIT, [LCD, 124]

        lsls OUT, 3
        str OUT, [LCD]
        str WRBIT, [LCD, CLR]
        adds X, 2
        ldrh OUT, [LINE, X]
        str WRBIT, [LCD, 124]

        lsls OUT, 3
        str OUT, [LCD]
        str WRBIT, [LCD, CLR]
        adds X, 2
        ldrh OUT, [LINE, X]
        str WRBIT, [LCD, 124]

        lsls OUT, 3
        str OUT, [LCD]
        str WRBIT, [LCD, CLR]
        adds X, 2
        ldrh OUT, [LINE, X]
        str WRBIT, [LCD, 124]

        blt 1b
        pop {r4-r6, pc}
.pool
.endFunc
.unreq LINE
.unreq X
.unreq LCD
.unreq TMP
.unreq WRBIT
.unreq CLR
.unreq OUT



```
