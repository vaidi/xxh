package diandian;

import lombok.Data;
import org.springframework.beans.BeanUtils;

public class BeanUtilsMain {

    public static void main(String[] args) {

        ErLong erLong = new ErLong();
        XXH xxh = new XXH();
        erLong.setName(111);
        BeanUtils.copyProperties(erLong,xxh);
        System.out.println(xxh.toString());
    }

}
@Data
class ErLong{

    private int name;

}
@Data
class XXH{

    private String name;

}