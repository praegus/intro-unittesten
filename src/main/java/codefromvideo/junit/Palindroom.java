package codefromvideo.junit;

public class Palindroom {

    public Palindroom() {
    }

    public boolean isPalindroom(String s) {
        return new StringBuilder(s).reverse().toString().equals(s);
    }
}
