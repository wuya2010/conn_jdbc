package conn;

/**
 * @author kylinWang
 * @data 2020/7/18 10:06
 */
public class Girl {
    private int gid;
    private String gname;

    public Girl(int gid, String gname) {
        this.gid = gid;
        this.gname = gname;
    }

    //get,set方法
    public int getGid() {
        return gid;
    }

    public String getGname() {
        return gname;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public void setGname(String gname) {
        this.gname = gname;
    }

    //重写 toString 方法
    public String toString(){
        return "gid, gname" + gid + gname;
    }



}
