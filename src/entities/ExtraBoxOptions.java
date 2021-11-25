package entities;

public class ExtraBoxOptions {
    private boolean wishes;
    private boolean withPost;
    private boolean showPhone;
    private boolean showName;

    public ExtraBoxOptions(boolean wishes, boolean withPost, boolean showPhone, boolean showName) {
        this.wishes = wishes;
        this.withPost = withPost;
        this.showPhone = showPhone;
        this.showName = showName;
    }

    public ExtraBoxOptions() {}
}
