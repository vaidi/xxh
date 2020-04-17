package my;
//https://www.cnblogs.com/skywang12345/p/3311915.html  开放寻址解决hash  对应的 还有 一个是链接发
public class IntSet2 {


    private Object[] _values = new Object[10];

    private int H(int value) {
        return value % 10;
    }

    private int LH(int value, int i) {
        return (H(value) + i) % 10;
    }

    public void Add(int item) throws Exception {
        int i = 0; // 已经探查过的槽的数量
        do {
            int j = LH(item, i); // 想要探查的地址
            if (_values[j] == null) {
                _values[j] = item;
                return;
            } else {
                i += 1;
            }
        } while (i <= 10);
        throw new Exception("集合溢出");
    }

    public boolean Contains(int item) {
        int i = 0; // 已经探查过的槽的数量
        int j = 0; // 想要探查的地址
        do {
            j = LH(item, i);
            if (_values[j] == null){
                return false;
            }else if  ((int) _values[j] == item){
                return true;
            } else{
                i += 1;
            }
        } while (i <= 10);
        return false;
    }

    public void Remove(int item) {
        // 有点不太好办
    }
}
