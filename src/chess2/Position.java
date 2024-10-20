package chess2;

import java.io.Serializable;

/**
 *
 * @author Christopher Payne
 */
public class Position implements Serializable {

    private final int r;
    private final int c;

    /**
     * just a small class to hold a position colonm and row
     * @param c colunm
     * @param r row
     */
    public Position(int c, int r) {
        this.r = c;
        this.c = r;
    }


    public int getR() {
        return this.r;
    }

    public int getC() {
        return this.c;
    }

    public String getStr() {
        return Character.toString((char) (this.c + 97)) + ((this.r) + 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Position position = (Position) obj;
        return r == position.r && c == position.c;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.r;
        hash = 67 * hash + this.c;
        return hash;
    }

    @Override
    public String toString() {
        return "r:" + ((this.r) + 1) + " c:" + Character.toString((char) (this.c + 97));
    }
}
