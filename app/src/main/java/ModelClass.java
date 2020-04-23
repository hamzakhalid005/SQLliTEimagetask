import android.graphics.Bitmap;

public class ModelClass {
    private String imagename;
    private Bitmap image;

    public ModelClass(String imagename, Bitmap image) {
        this.imagename = imagename;
        this.image = image;
    }

    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
