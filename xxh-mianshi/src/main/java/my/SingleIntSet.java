package my;

public class SingleIntSet {


    private Object[] _values = new Object[20];

    public void Add(int item) {
        _values[item] = item;
    }

    public void Remove(int item) {
        _values[item] = null;
    }

    public boolean Contains(int item) {
        if (_values[item] == null) {
            return false;

        } else {
            return (int) _values[item] == item;
        }
    }

    public static void main(String[] args) {
        SingleIntSet set = new SingleIntSet();
        set.Add(13);
        set.Add(17);
        System.out.println(set.Contains(13));
        System.out.println(set.Contains(15));
    }


}
