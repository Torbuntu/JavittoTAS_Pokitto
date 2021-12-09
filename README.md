# Javitto TAS for Pokitto

git clone this repo into `FemtoIDE/projects` to work with it in FemtoIDE.

Following thread on the Pokitto forum [TAS mode for Javitto](https://talk.pokitto.com/t/tas-mode-for-javitto-development-thread)


# Patches to the femto java

[Sprite.java](https://github.com/felipemanga/FemtoIDE/blob/master/javacompiler/femto/Sprite.java)

add:

```Java
public void draw( TASMode screen ){
	updateTasAnimation();
	float x = this.x;
	float y = this.y;
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