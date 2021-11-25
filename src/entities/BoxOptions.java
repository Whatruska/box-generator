package entities;

public class BoxOptions {
    private String name;
    private String id;
    private int pictureOption;
    private int maxSum;
    private ExtraBoxOptions extras;

    public BoxOptions(String name, String id, int pictureOption, int maxSum, ExtraBoxOptions extras) {
        this.name = name;
        this.id = id;
        this.pictureOption = pictureOption;
        this.maxSum = maxSum;
        this.extras = extras;
    }

    public BoxOptions() {}
}
