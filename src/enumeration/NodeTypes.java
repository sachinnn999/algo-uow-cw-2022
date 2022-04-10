package enumeration;
/** *****************************************************************************
 *  Name:    Sachin De Silva
 *  UOW ID:   W1761382
 *  IIT ID: 2019801
 *
 *  Description:  node types details can be found here
 *
 *  Written:       02-04-2022
 *  Last updated:  11-02-2022
 *
 **************************************************************************** */
public enum NodeTypes {
    SNOW("*",false),
    ROCK("0", true),
    START("S", false),
    FINISH("F", false),
        ;

    private final String code;
    private final boolean isBlock;

    NodeTypes(String code, boolean isBlock) {
        this.code = code;
        this.isBlock = isBlock;
    }

    public String getCode() {
        return code;
    }

    public boolean isBlock() {
        return isBlock;
    }

    public static NodeTypes getValue(final String code) {
        NodeTypes value = null;
        for (NodeTypes i : NodeTypes.values()) {
            if (i.getCode().contentEquals(code)) {
                value = i;
                break;
            }
        }
        return value;
    }
}
