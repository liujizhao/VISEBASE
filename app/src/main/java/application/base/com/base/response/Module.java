package application.base.com.base.response;

/**
 * Author Blank
 * Create on 2018/8/3 14:38
 * Description:
 */
public class Module {
    private int drawableId;
    private int type;
    private int width = 100;
    private int height = 80;

    public Module(int drawableId, int type) {
        this.drawableId = drawableId;
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
