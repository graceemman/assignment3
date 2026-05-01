 public class MyTestingClass {
    int a;
    int b;

    public MyTestingClass(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + a;
        result = result * 31 + b;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyTestingClass)) return false;
        MyTestingClass other = (MyTestingClass) o;
        return a == other.a && b == other.b;
    }
}